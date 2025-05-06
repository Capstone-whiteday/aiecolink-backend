package com.whiteday.aiecolink.member;

import com.whiteday.aiecolink.member.dto.UserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> create(@RequestBody UserRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
    }
}
