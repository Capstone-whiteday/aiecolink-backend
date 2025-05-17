package com.whiteday.aiecolink.domain.station.service;

import com.whiteday.aiecolink.domain.station.model.request.RegionResponseDto;
import com.whiteday.aiecolink.domain.station.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<RegionResponseDto> getAllRegions() {
        return regionRepository.findAll().stream()
                .map(region -> new RegionResponseDto(
                        region.getRegionId(),
                        region.getRegionName()
                ))
                .toList();
    }
}
