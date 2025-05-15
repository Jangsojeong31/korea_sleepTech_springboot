package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.admin.request.PromoteToAdminRequestDto;
import com.example.korea_sleepTech_springboot.dto.admin.response.PromoteToAdminResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;

public interface AdminService {

    ResponseDto<PromoteToAdminResponseDto> promoteUserToAdmin(PromoteToAdminRequestDto dto);
}
