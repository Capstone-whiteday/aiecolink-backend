package com.whiteday.aiecolink.domain.member.service;

import com.whiteday.aiecolink.domain.member.dto.UserProfileUpdateRequestDto;
import com.whiteday.aiecolink.domain.member.model.Role;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import com.whiteday.aiecolink.domain.member.repository.UserRepository;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import com.whiteday.aiecolink.domain.member.dto.UserProfileResponseDto;
import com.whiteday.aiecolink.domain.member.dto.UserRequestDto;
import com.whiteday.aiecolink.domain.member.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerUser(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = User.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .activated(requestDto.getActivated() != null ? requestDto.getActivated() : true)
                .role(requestDto.getRole() != null ? requestDto.getRole() : Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return userRepository.save(user).getUserId();
    }

    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return UserResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .activated(user.getActivated())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public void updateUserProfile(User user, UserProfileUpdateRequestDto request) {
        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        // 이름과 비밀번호 업데이트
        user.updateNameAndPassword(
                request.getNewName(),
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);
    }
    public UserProfileResponseDto getProfile(User user) {
        return new UserProfileResponseDto(user.getName());
    }

}