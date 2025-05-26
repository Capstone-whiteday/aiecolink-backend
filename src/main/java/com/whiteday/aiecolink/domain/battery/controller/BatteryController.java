package com.whiteday.aiecolink.domain.battery.controller;

import com.whiteday.aiecolink.domain.battery.BatteryRegisterRes;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import com.whiteday.aiecolink.domain.member.security.CustomUserDetails;
import com.whiteday.aiecolink.domain.battery.service.BatteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/battery")
public class BatteryController {
    private final BatteryService batteryService;
    // 베터리 등록
    @PostMapping("/register/{stationId}")
    public ResponseEntity<BatteryRegisterRes> create(@PathVariable Long stationId, @AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody float batteryCapacity){
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(batteryService.register(stationId,user,batteryCapacity));
    }

    // 베터리 조회
    @GetMapping("/{batteryId}")
    public ResponseEntity<BatteryRegisterRes> read(@PathVariable Long batteryId, @AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(batteryService.read(batteryId,user));
    }

    // 베터리 수정
    @PutMapping("/{batteryId}")
    public ResponseEntity<BatteryRegisterRes> update(@PathVariable Long batteryId, @AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody float batteryCapacity){
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(batteryService.update(batteryId,user,batteryCapacity));
    }

}
