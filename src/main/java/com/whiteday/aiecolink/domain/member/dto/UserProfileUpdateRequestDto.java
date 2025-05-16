package com.whiteday.aiecolink.domain.member.dto;

import lombok.Getter;

@Getter
public class UserProfileUpdateRequestDto {
    private String currentPassword;
    private String newName;
    private String newPassword;
}
