package com.whiteday.aiecolink.admin;

import com.whiteday.aiecolink.member.Role;
import com.whiteday.aiecolink.member.User;
import com.whiteday.aiecolink.member.UserRepository;
import com.whiteday.aiecolink.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signupAdmin(@RequestBody AdminSignupRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다.");
        }

        User admin = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .activated(true)
                .role(Role.ADMIN)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(admin);

        return ResponseEntity.ok().body("관리자 회원가입 성공");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequestDto requestDto) {
        User admin = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), admin.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (admin.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("관리자 권한이 없습니다.");
        }

        String token = jwtTokenProvider.createToken(admin.getEmail(), admin.getRole().name());

        return ResponseEntity.ok().body(token);
    }
}
