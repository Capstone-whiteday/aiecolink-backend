package com.whiteday.aiecolink.global.config;

import com.whiteday.aiecolink.jwt.JwtTokenProvider;
import com.whiteday.aiecolink.jwt.JwtAuthenticationFilter;
import com.whiteday.aiecolink.member.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화 (API 테스트 시 유용)
                .formLogin(form -> form.disable())  // Form 로그인 비활성화
                .httpBasic(basic -> basic.disable())  // HTTP Basic 인증 비활성화
                .authorizeHttpRequests(auth -> auth
                        // ✅ Swagger 경로 허용
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()

                        // ✅ 인증/관리자/테스트 관련 경로 허용
                        .requestMatchers(
                                "/auth/**",
                                "/admin/signup",
                                "/admin/login",
                                "/api/test/**"
                        ).permitAll()

                        // ✅ 그 외는 인증 필요
                        .anyRequest().authenticated()
                )
                // ✅ JWT 인증 필터 등록
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenProvider, customUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
