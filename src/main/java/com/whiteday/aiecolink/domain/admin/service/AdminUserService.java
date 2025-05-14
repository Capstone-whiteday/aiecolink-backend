package com.whiteday.aiecolink.domain.admin.service;

import com.whiteday.aiecolink.domain.admin.dto.AdminProfileResponseDto;
import com.whiteday.aiecolink.domain.admin.dto.AdminProfileUpdateRequestDto;
import com.whiteday.aiecolink.domain.member.model.Role;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import com.whiteday.aiecolink.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

/*    // 관리자 권한으로 특정 유저 정보 수정
    public void updateUserProfile(Long userId, UpdateUserProfileRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        User updatedUser = User.builder()
                .userId(user.getUserId())
                .name(request.getName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(request.getNewPassword()))
                .activated(user.getActivated())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(updatedUser);
    }*/

    // 관리자 본인 프로필 조회
    public AdminProfileResponseDto getAdminProfile(User admin) {
        return new AdminProfileResponseDto(admin.getName(), admin.getEmail());
    }

    // 관리자 본인 프로필 수정
    public void updateAdminProfile(User admin, AdminProfileUpdateRequestDto request) {
        // ✅ 기존 비밀번호 검증
        if (!passwordEncoder.matches(request.getCurrentPassword(), admin.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        // ✅ 이름 및 비밀번호 업데이트
        admin.updateNameAndPassword(
                request.getNewName(),
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(admin);
    }

    public void deleteOtherAdminByEmail(String targetEmail, User requester) {
        if (requester.getEmail().equals(targetEmail)) {
            throw new CustomException(ErrorCode.CANNOT_DELETE_SELF);
        }

        User targetAdmin = userRepository.findByEmail(targetEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.ADMIN_NOT_FOUND));

        if (targetAdmin.getRole() != Role.ADMIN) {
            throw new CustomException(ErrorCode.NOT_AN_ADMIN);
        }

        userRepository.delete(targetAdmin);
    }


}
