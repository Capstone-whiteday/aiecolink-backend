package com.whiteday.aiecolink.domain.station.controller;

import com.whiteday.aiecolink.domain.station.model.StationRegisterRes;
import com.whiteday.aiecolink.domain.station.model.request.StationRegisterReq;
import com.whiteday.aiecolink.domain.station.service.StationService;
import com.whiteday.aiecolink.global.CustomUserDetails;
import com.whiteday.aiecolink.member.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/station")
public class StationController {
    private final StationService stationService;

    @PostMapping("/register")
    public ResponseEntity<StationRegisterRes> create(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody StationRegisterReq stationRegisterReq){
        User user = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(stationService.register(user,stationRegisterReq));
    }
}
