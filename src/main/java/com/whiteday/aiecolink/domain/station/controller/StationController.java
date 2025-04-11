package com.whiteday.aiecolink.domain.station.controller;

import com.whiteday.aiecolink.domain.station.model.StationRegisterRes;
import com.whiteday.aiecolink.domain.station.model.request.StationRegisterReq;
import com.whiteday.aiecolink.domain.station.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/station")
public class StationController {
    private final StationService stationService;

    @PostMapping("/register")
    public ResponseEntity<StationRegisterRes> create(@RequestBody StationRegisterReq stationRegisterReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(stationService.register(stationRegisterReq));
    }
}
