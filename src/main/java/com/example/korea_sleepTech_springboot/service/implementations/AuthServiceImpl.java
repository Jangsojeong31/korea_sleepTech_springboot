package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.constants.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.auth.PasswordResetRequestDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignInRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserSignUpRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignInResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.response.UserSignUpResponseDto;
import com.example.korea_sleepTech_springboot.entity.Role;
import com.example.korea_sleepTech_springboot.entity.User;
import com.example.korea_sleepTech_springboot.provider.JwtProvider;
import com.example.korea_sleepTech_springboot.repository.RoleRepository;
import com.example.korea_sleepTech_springboot.repository.UserRepository;
import com.example.korea_sleepTech_springboot.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    // 회원가입
    @Override
    public ResponseDto<UserSignUpResponseDto> signup(UserSignUpRequestDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();

        UserSignUpResponseDto data = null;
        User user = null;

        // 패스워드 일치 여부 확인
        if (!password.equals(confirmPassword)) {
            // 일치하지 않은 경우
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        // 중복되는 이메일 검증
        if (userRepository.existsByEmail(email)) { // 중복되는 경우
            return ResponseDto.setFailed(ResponseMessage.EXIST_DATA);
        }

        // 패스워드 암호화
        String encodePassword = bCryptPasswordEncoder.encode(password);

        // 권한 정보 확인
        Role userRole = roleRepository.findByRoleName("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().roleName("USER").build())); // 없으면 생성후 가져오기

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(userRole);

        // User 생성
        user = User.builder()
                .email(email)
                .password(encodePassword)
                .createdAt(LocalDateTime.now())
                .roles(roleSet)
                .build();

        userRepository.save(user);

        data = new UserSignUpResponseDto(user);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 로그인
    @Override
    public ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        UserSignInResponseDto data = null;
//        User user = null;

        int exprTime = jwtProvider.getExpiration();;

//        user = userRepository.findByEmail(email)
//                .orElseThrow(null);
//
//        if (user == null) {
//            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
//        }

        // 예외 처리
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_USER));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            // .matches(평문 비밀번호, 암호화된 비밀번호)
            // : 평문 비밀번호(실제 비밀번호)와 암호화된 비밀번호를 비교하여 일치 여부 반환(boolean)
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        // 사용자 정보의 권한 정보를 호출
        Set<String> roles = user.getRoles().stream()
                .map(Role::getRoleName)
                // role -> role.getRoleName 과 동일
                .collect(Collectors.toSet());

        // 토큰 생성
        String token = jwtProvider.generateJwtToken(email, roles); // username에 email 저장

        data = new UserSignInResponseDto(token, user, exprTime);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    @Override
    public Mono<ResponseEntity<String>> resetPassword(PasswordResetRequestDto dto) {
        return Mono.fromCallable(() -> {
            User user = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(((() -> new IllegalArgumentException("가입된 이메일이 아닙니다."))));
//            if(!user.isEmailVerified()) {
//                return ResponseEntity.badRequest().body("이메일 인증이 필요합니다.");
//            }

            // 비밀번호, 비밀번호 확인 유효성 검사 필수! (일치 여부, 형식 여부)
            user.setPassword(bCryptPasswordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);

            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");

        }).onErrorResume(e -> Mono.just(
                ResponseEntity.badRequest().body("비밀번호 재설정 실패: " + e.getMessage())
        )).subscribeOn(Schedulers.boundedElastic());
    }
}
