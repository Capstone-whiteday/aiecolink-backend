package com.whiteday.aiecolink.domain.station.service;

import com.whiteday.aiecolink.domain.station.model.StationRegisterRes;
import com.whiteday.aiecolink.domain.station.model.entity.Station;
import com.whiteday.aiecolink.domain.station.model.request.StationRegisterReq;
import com.whiteday.aiecolink.domain.station.repository.StationRepository;
import com.whiteday.aiecolink.domain.user.model.User;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public String register(User user, StationRegisterReq registerReq){
        // 중복 체크
        stationRepository.findByStationName(registerReq.getStationName())
                .ifPresent(x ->{
                    throw new CustomException(ErrorCode.STATION_ALREADY_EXISTS);
                });

        Station station = stationRepository.save(Station.builder()
                .name(registerReq.getStationName())
                .location(registerReq.getLocation())
                .user(user)
                .build()
        );
        
        return "충전소 등록";
    }
}
