package com.whiteday.aiecolink.member;

import com.whiteday.aiecolink.member.dto.UserRequestDto;
import com.whiteday.aiecolink.member.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long registerUser(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword()) // 실제로는 암호화 필요
                .activated(requestDto.getActivated() != null ? requestDto.getActivated() : true)
                .role(requestDto.getRole() != null ? requestDto.getRole() : Role.USER)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        return userRepository.save(user).getUser_id();
    }

    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return UserResponseDto.builder()
                .user_id(user.getUser_id())
                .email(user.getEmail())
                .activated(user.getActivated())
                .role(user.getRole())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .build();
    }
}
