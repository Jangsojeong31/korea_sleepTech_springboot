package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.admin.request.PromoteToAdminRequestDto;
import com.example.korea_sleepTech_springboot.dto.admin.response.PromoteToAdminResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.ADMIN_API)
public class AdminController {

    private final AdminService adminService;

    // === AdminController mapping pattern === //
    private static final String PUT_AUTHORITY_TO_ADMIN = "/promote";

    // 권한 승격
    @PreAuthorize("hasRole('ADMIN')") // ADMIN 사용자가 없는 상태에서는 에러 발생 가능
    @PutMapping(PUT_AUTHORITY_TO_ADMIN)
    public ResponseEntity<ResponseDto<PromoteToAdminResponseDto>> promoteUserToAdmin(
            @RequestBody PromoteToAdminRequestDto dto
    ) {
        ResponseDto<PromoteToAdminResponseDto> response = adminService.promoteUserToAdmin(dto);
        return ResponseEntity.ok(response);
    }
}
