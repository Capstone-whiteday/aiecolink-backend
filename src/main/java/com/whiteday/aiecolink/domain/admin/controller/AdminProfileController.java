package com.whiteday.aiecolink.domain.admin.controller;

import com.whiteday.aiecolink.domain.admin.service.AdminUserService;
import com.whiteday.aiecolink.domain.admin.dto.AdminProfileResponseDto;
import com.whiteday.aiecolink.domain.admin.dto.AdminProfileUpdateRequestDto;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import com.whiteday.aiecolink.domain.member.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/profile")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "BearerAuth")
public class AdminProfileController {

    private final AdminUserService adminUserService;

    @Operation(summary = "관리자 프로필 조회", description = "로그인한 관리자 자신의 이름과 이메일을 반환합니다.")
    @GetMapping
    public ResponseEntity<AdminProfileResponseDto> getProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        User admin = userDetails.getUser();
        return ResponseEntity.ok(adminUserService.getAdminProfile(admin));
    }

    @Operation(summary = "관리자 프로필 수정", description = "로그인한 관리자의 이름과 비밀번호를 수정합니다.")
    @PutMapping
    public ResponseEntity<String> updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody AdminProfileUpdateRequestDto request) {

        User admin = userDetails.getUser();
        adminUserService.updateAdminProfile(admin, request);
        return ResponseEntity.ok("관리자 프로필이 수정되었습니다.");
    }
}
