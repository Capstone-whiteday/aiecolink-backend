package com.whiteday.aiecolink.domain.station.controller;

import com.whiteday.aiecolink.domain.station.model.request.StationRegisterReq;
import com.whiteday.aiecolink.domain.station.service.StationService;
import com.whiteday.aiecolink.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StationController {
    private final StationService stationService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user, @RequestBody StationRegisterReq stationRegisterReq){
        return ResponseEntity.ok(stationService.register(user,stationRegisterReq));
    }
}
