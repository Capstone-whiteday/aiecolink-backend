package com.whiteday.aiecolink.domain.admin.controller;

import com.whiteday.aiecolink.domain.admin.service.AdminUserService;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import com.whiteday.aiecolink.domain.member.repository.UserRepository;
import com.whiteday.aiecolink.domain.member.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAccountController {

    private final UserRepository userRepository;
    private final AdminUserService adminUserService;

    @GetMapping("/users")
    @Operation(summary = "회원 목록 조회", description = "관리자가 모든 사용자의 목록을 조회합니다.")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/admins")
    @Operation(summary = "관리자 삭제", description = "이메일을 기반으로 다른 관리자 계정을 삭제합니다.")
    @SecurityRequirement(name = "BearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAdminByEmail(
            @RequestParam String email,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        User requester = currentUser.getUser();
        adminUserService.deleteOtherAdminByEmail(email, requester);
        return ResponseEntity.ok("관리자 계정이 삭제되었습니다.");
    }



}
