package com.whiteday.aiecolink.member;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private Boolean activated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;
}
