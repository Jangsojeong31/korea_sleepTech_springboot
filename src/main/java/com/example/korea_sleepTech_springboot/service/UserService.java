package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.dto.user.request.UserUpdateRequestDto;
import com.example.korea_sleepTech_springboot.dto.user.response.GetUserResponseDto;
import jakarta.validation.Valid;

public interface UserService {
    ResponseDto<GetUserResponseDto> getUserInfo(String userEmail);

    ResponseDto<GetUserResponseDto> updateUserInfo(String userEmail, @Valid UserUpdateRequestDto dto);

    ResponseDto<Void> deleteUser(String userEmail);
}
