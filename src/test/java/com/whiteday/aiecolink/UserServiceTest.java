package com.whiteday.aiecolink;
import com.whiteday.aiecolink.member.Role;
import com.whiteday.aiecolink.member.UserService;
import com.whiteday.aiecolink.member.dto.UserRequestDto;
import com.whiteday.aiecolink.member.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // AiecolinkApplication.class 를 자동으로 읽음
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void 사용자등록_및_조회테스트() {
        // given
        UserRequestDto requestDto = UserRequestDto.builder()
                .email("김진주@gmail.com") // 이메일 변경
                .password("test1234")
                .activated(true)
                .role(Role.USER)
                .build();

        // when
        Long userId = userService.registerUser(requestDto);
        UserResponseDto user = userService.getUserByEmail("김진주@gmail.com");

        // then
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("김진주@gmail.com");
        assertThat(user.getRole()).isEqualTo(Role.USER);
    }

}
