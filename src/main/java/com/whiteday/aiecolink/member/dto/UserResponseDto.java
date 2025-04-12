package com.whiteday.aiecolink.member.dto;

import com.whiteday.aiecolink.member.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long user_id;
    private String email;
    private Boolean activated;
    private Role role;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
