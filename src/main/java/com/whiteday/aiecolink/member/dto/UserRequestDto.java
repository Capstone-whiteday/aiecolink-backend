package com.whiteday.aiecolink.member.dto;

import com.whiteday.aiecolink.member.Role;
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
