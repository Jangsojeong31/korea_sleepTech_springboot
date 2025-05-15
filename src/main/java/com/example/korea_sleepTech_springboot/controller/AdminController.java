package com.example.korea_sleepTech_springboot.controller;

import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.admin.request.PutAuthorityRequestDto;
import com.example.korea_sleepTech_springboot.dto.admin.response.DemoteFromAdminResponseDto;
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
    private static final String PUT_AUTHORITY_DEMOTE = "/demote";

    // 권한 승격
    @PreAuthorize("hasRole('ADMIN')") // ADMIN 사용자가 없는 상태에서는 에러 발생 가능
    @PutMapping(PUT_AUTHORITY_TO_ADMIN)
    public ResponseEntity<ResponseDto<PromoteToAdminResponseDto>> promoteUserToAdmin(
            @RequestBody PutAuthorityRequestDto dto
    ) {
        ResponseDto<PromoteToAdminResponseDto> response = adminService.promoteUserToAdmin(dto);
        return ResponseEntity.ok(response);
    }

    // 권한 회수
    @PreAuthorize("hasRole('ADMIN')") // ADMIN 사용자가 없는 상태에서는 에러 발생 가능
    @PutMapping(PUT_AUTHORITY_DEMOTE)
    public ResponseEntity<ResponseDto<DemoteFromAdminResponseDto>> demoteUserFromAdmin(
            @RequestBody PutAuthorityRequestDto dto
    ) {
        ResponseDto<DemoteFromAdminResponseDto> response = adminService.demoteUserFromAdmin(dto);
        return ResponseEntity.ok(response);
    }
}
