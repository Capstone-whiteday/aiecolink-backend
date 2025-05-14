package com.whiteday.aiecolink.domain.member.controller;

import com.whiteday.aiecolink.domain.member.dto.UserLoginRequestDto;
import com.whiteday.aiecolink.domain.member.dto.UserSignupRequestDto;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import com.whiteday.aiecolink.jwt.JwtTokenProvider;
import com.whiteday.aiecolink.domain.member.model.Role;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import com.whiteday.aiecolink.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
        return ResponseEntity.ok().body(Map.of(
                "success", true,
                "token", token
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .activated(true)
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
        // ✅ JSON 형태로 응답
        return ResponseEntity.ok().body(Map.of(
                "success", true,
                "message", "회원가입 성공"
        ));
    }
}
