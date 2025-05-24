package com.whiteday.aiecolink.domain.station.controller;

import com.whiteday.aiecolink.domain.station.model.request.RegionResponseDto;
import com.whiteday.aiecolink.domain.station.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/list")
    public List<RegionResponseDto> getRegionList() {
        return regionService.getAllRegions();
    }
}
