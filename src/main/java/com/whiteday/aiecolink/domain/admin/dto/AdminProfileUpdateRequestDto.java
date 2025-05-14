package com.whiteday.aiecolink.domain.admin.dto;

import lombok.Getter;

@Getter
public class AdminProfileUpdateRequestDto {
    private String currentPassword;
    private String newName;
    private String newPassword;
}