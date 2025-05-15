package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.admin.request.PromoteToAdminRequestDto;
import com.example.korea_sleepTech_springboot.dto.admin.response.PromoteToAdminResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.entity.Role;
import com.example.korea_sleepTech_springboot.entity.User;
import com.example.korea_sleepTech_springboot.repository.RoleRepository;
import com.example.korea_sleepTech_springboot.repository.UserRepository;
import com.example.korea_sleepTech_springboot.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public ResponseDto<PromoteToAdminResponseDto> promoteUserToAdmin(PromoteToAdminRequestDto dto) {
        PromoteToAdminResponseDto data = null;

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_USER));

        Role adminRole = roleRepository.findByRoleName("ADMIN")
                .orElseGet(() -> roleRepository.save(Role.builder().roleName("ADMIN").build())); // 없으면 만들어서 가져옴

        // ADMIN 권한이 이미 있는지 확인
        boolean alreadyHasAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("ADMIN")); // true -> ADMIN 권한 존재
        // .anyMatch(): 각 요소를 순회하며 조건에 부합하는 요소가 단 하나라도 존재하면 true 반환, 그렇지 않으면 false 반환

        if (alreadyHasAdmin) {
            throw new IllegalArgumentException("이미 ADMIN 권한을 가지고 있습니다.");
        }

        // 권한이 없으면 추가
        user.getRoles().add(adminRole);

        User savedUser = userRepository.save(user);

        // Set을 List로 변환 (PromoteToAdminResponseDto에서는 List 형태의 필드이기 때문에)
        List<String> roleList = savedUser.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        data = new PromoteToAdminResponseDto(
                user.getEmail(), roleList, "권한 변경이 정상적으로 이루어졌습니다."
        );

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }
}
