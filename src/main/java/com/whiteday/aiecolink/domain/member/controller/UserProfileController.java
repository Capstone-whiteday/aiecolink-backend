package com.whiteday.aiecolink.domain.member.controller;

import com.whiteday.aiecolink.domain.member.dto.UserProfileUpdateRequestDto;
import com.whiteday.aiecolink.domain.member.service.UserService;
import com.whiteday.aiecolink.domain.member.dto.UserProfileResponseDto;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import com.whiteday.aiecolink.domain.member.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {
    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @SecurityRequirement(name = "BearerAuth")
    @Operation(summary = "회원 프로필 조회", description = "로그인한 사용자의 이름을 반환합니다.")
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponseDto> getProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(userService.getProfile(user));
    }

    @SecurityRequirement(name = "BearerAuth")
    @Operation(summary = "회원 프로필 수정", description = "로그인한 사용자의 이름과 비밀번호를 수정합니다.")
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UserProfileUpdateRequestDto request) {
        User user = userDetails.getUser();
        userService.updateUserProfile(user, request);
        return ResponseEntity.ok("회원 프로필이 수정되었습니다.");
    }
}
