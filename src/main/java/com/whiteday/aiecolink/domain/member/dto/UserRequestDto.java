package com.whiteday.aiecolink.domain.member.dto;

import com.whiteday.aiecolink.domain.member.model.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String email;
    private String password;
    private Boolean activated;
    private Role role;
}
