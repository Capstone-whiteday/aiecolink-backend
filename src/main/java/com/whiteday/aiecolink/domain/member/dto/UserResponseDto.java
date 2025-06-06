package com.whiteday.aiecolink.domain.member.dto;

import com.whiteday.aiecolink.domain.member.model.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long userId;
    private String email;
    private Boolean activated;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
