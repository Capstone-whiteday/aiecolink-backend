/*package com.whiteday.aiecolink.domain.admin;

import com.whiteday.aiecolink.domain.admin.UpdateUserProfileRequest;
import com.whiteday.aiecolink.member.User;
import com.whiteday.aiecolink.member.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserRepository userRepository;
    private final AdminUserService adminUserService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{userId}/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UpdateUserProfileRequest request) {

        adminUserService.updateUserProfile(userId, request);
        return ResponseEntity.ok("사용자 정보가 성공적으로 수정되었습니다.");
    }
}
*/