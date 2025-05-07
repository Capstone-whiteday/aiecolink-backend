package com.whiteday.aiecolink.domain.station.controller;
import com.whiteday.aiecolink.domain.station.model.StationRegisterRes;
import com.whiteday.aiecolink.domain.station.model.StationRes;
import com.whiteday.aiecolink.domain.station.model.StationSummaryRes;
import com.whiteday.aiecolink.domain.station.model.request.StationRegisterReq;
import com.whiteday.aiecolink.domain.station.model.request.StationUpdateReq;
import com.whiteday.aiecolink.domain.station.service.StationService;
import com.whiteday.aiecolink.global.CustomUserDetails;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import com.whiteday.aiecolink.member.Role;
import com.whiteday.aiecolink.member.User;
import com.whiteday.aiecolink.member.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/test/station")
@RequiredArgsConstructor
public class TestStationController {
    private final UserRepository userRepository;
    private final StationService stationService;

    @PostMapping("/init-user")
    public ResponseEntity<String> initTestUser() {
        if (userRepository.findByEmail("test@dummy.com").isEmpty()) {
            userRepository.save(User.builder()
                    .email("test@dummy.com")
                    .password("test")
                    .role(Role.USER)
                    .activated(true)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build());
        }
        return ResponseEntity.ok("Test user initialized.");
    }

    // 춭전소 등록
    @PostMapping("/register")
    public ResponseEntity<StationRegisterRes> testRegister(@RequestBody StationRegisterReq stationRegisterReq) {
        // 테스트 사용자 조회 (없으면 예외)
        User testUser = userRepository.findByEmail("test@dummy.com")
                .orElseThrow(() -> new CustomException(ErrorCode.TEST_USER_NOT_EXISTS));

        // 실제 서비스 로직 재사용
        StationRegisterRes response = stationService.register(testUser, stationRegisterReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 충전소 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<StationSummaryRes>> readList(){
        // 테스트 사용자 조회 (없으면 예외)
        User testUser = userRepository.findByEmail("test@dummy.com")
                .orElseThrow(() -> new CustomException(ErrorCode.TEST_USER_NOT_EXISTS));
        return ResponseEntity.status(HttpStatus.OK).body(stationService.readList(testUser));
    }

    // 충전소 상세 조회
    @GetMapping("/{stationId}")
    public ResponseEntity<StationRes> readDetail(@PathVariable Long stationId) {
        // 테스트 사용자 조회 (없으면 예외)
        User testUser = userRepository.findByEmail("test@dummy.com")
                .orElseThrow(() -> new CustomException(ErrorCode.TEST_USER_NOT_EXISTS));
        StationRes response = stationService.readDetail(stationId,testUser);
        return ResponseEntity.ok(response);
    }

    // 충전소 수정
    @PutMapping("/{stationId}")
    public ResponseEntity<StationRes> updateStation(
            @PathVariable Long stationId,
            @RequestBody StationUpdateReq stationUpdateReq) {
        // 테스트 사용자 조회 (없으면 예외)
        User testUser = userRepository.findByEmail("test@dummy.com")
                .orElseThrow(() -> new CustomException(ErrorCode.TEST_USER_NOT_EXISTS));

        StationRes updatedStation = stationService.update(stationId, testUser, stationUpdateReq);

        return ResponseEntity.ok(updatedStation);
    }

    // 충전소 삭제
    @DeleteMapping("/{stationId}")
    public String deleteStation(@PathVariable Long stationId) {
        // 테스트 사용자 조회 (없으면 예외)
        User testUser = userRepository.findByEmail("test@dummy.com")
                .orElseThrow(() -> new CustomException(ErrorCode.TEST_USER_NOT_EXISTS));
        stationService.delete(stationId, testUser);

        return "테스트 충전소 삭제";
    }


}
