package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.constants.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserUpdateRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.response.GetUserResponseDto;
import com.example.korea_sleepTech_springboot.entity.User;
import com.example.korea_sleepTech_springboot.repository.UserRepository;
import com.example.korea_sleepTech_springboot.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 1) 회원 정보 조회
    @Override
    public ResponseDto<GetUserResponseDto> getUserInfo(String userEmail) {
//        User user = userRepository.findByEmail(userEmail)
//                .orElse(null); // 오류 발생시키지 않음
//
//        if(user == null){
//            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
//        }

        // 예외 처리
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_USER));

        GetUserResponseDto data = new GetUserResponseDto(user);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 2) 회원 정보 수정
    @Override
    public ResponseDto<GetUserResponseDto> updateUserInfo(String userEmail, UserUpdateRequestDto dto) {
        User user = userRepository.findByEmail(userEmail)
                .orElse(null); // 오류 발생시키지 않음

        if(user == null){
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) { // 비밀버호가 아예 없는 경우와 공백만 있는 경우(정상이 아닌 경우)를 검증
            if (!dto.getPassword().equals(dto.getConfirmPassword())) { // 비밀번호는 정상이나, 비밀번호 확인과 일치하지 않는 경우
//                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
                throw new IllegalArgumentException(ResponseMessage.NOT_MATCH_PASSWORD); // 예외 처리
            }
            // 비밀번호가 정상이고, 비밀번호 확인과 일치하는 경우
            String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
            user.setPassword(encodedPassword);

        }

        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        GetUserResponseDto data = new GetUserResponseDto(user);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 3) 회원 삭제
    @Override
    public ResponseDto<Void> deleteUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXIST_USER));

        userRepository.delete(user);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }


}

