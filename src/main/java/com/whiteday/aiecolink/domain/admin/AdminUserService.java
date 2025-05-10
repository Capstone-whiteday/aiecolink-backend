/*package com.whiteday.aiecolink.domain.admin;

import com.whiteday.aiecolink.domain.admin.UpdateUserProfileRequest;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import com.whiteday.aiecolink.member.User;
import com.whiteday.aiecolink.member.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void updateUserProfile(Long userId, UpdateUserProfileRequest request) {
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
    }
}
*/