package com.whiteday.aiecolink.domain.station.controller;

import com.whiteday.aiecolink.domain.station.model.StationRegisterRes;
import com.whiteday.aiecolink.domain.station.model.StationRes;
import com.whiteday.aiecolink.domain.station.model.StationSummaryRes;
import com.whiteday.aiecolink.domain.station.model.request.StationRegisterReq;
import com.whiteday.aiecolink.domain.station.model.request.StationUpdateReq;
import com.whiteday.aiecolink.domain.station.service.StationService;
import com.whiteday.aiecolink.domain.member.security.CustomUserDetails;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/station")
public class StationController {
    private final StationService stationService;

    // 충전소 등록
    @PostMapping("/register")
    public ResponseEntity<StationRegisterRes> create(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody StationRegisterReq stationRegisterReq){
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(stationService.register(user,stationRegisterReq));
    }

    // 충전소 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<StationSummaryRes>> readList(@AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(stationService.readList(user));
    }

    // 충전소 상세 조회
    @GetMapping("/{stationId}")
    public ResponseEntity<StationRes> readDetail(
            @PathVariable Long stationId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        StationRes response = stationService.readDetail(stationId, userDetails.getUser());
        return ResponseEntity.ok(response);
    }

    // 충전소 수정
    @PutMapping("/{stationId}")
    public ResponseEntity<StationRes> updateStation(
            @PathVariable Long stationId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody StationUpdateReq stationUpdateReq) {

        User user = userDetails.getUser(); // 인증된 사용자
        StationRes updatedStation = stationService.update(stationId, user, stationUpdateReq);

        return ResponseEntity.ok(updatedStation);
    }

    // 충전소 삭제
    @DeleteMapping("/{stationId}")
    public String deleteStation(
            @PathVariable Long stationId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        User user = userDetails.getUser();
        stationService.delete(stationId, user);

        return "충전소 삭제";
    }



}
