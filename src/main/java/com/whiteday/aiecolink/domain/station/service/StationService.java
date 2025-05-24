package com.whiteday.aiecolink.domain.station.service;

import com.whiteday.aiecolink.domain.scheduling.service.BatteryService;
import com.whiteday.aiecolink.domain.station.model.StationRegisterRes;
import com.whiteday.aiecolink.domain.station.model.StationRes;
import com.whiteday.aiecolink.domain.station.model.StationSummaryRes;
import com.whiteday.aiecolink.domain.station.model.entity.Region;
import com.whiteday.aiecolink.domain.station.model.entity.Station;
import com.whiteday.aiecolink.domain.station.model.request.StationRegisterReq;
import com.whiteday.aiecolink.domain.station.model.request.StationUpdateReq;
import com.whiteday.aiecolink.domain.station.repository.RegionRepository;
import com.whiteday.aiecolink.domain.station.repository.StationRepository;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StationService {

    private final StationRepository stationRepository;
    private final RegionRepository regionRepository;
    private final BatteryService batteryService;

    public StationRegisterRes register(User user, StationRegisterReq registerReq){
        // 중복 체크
        stationRepository.findByStationName(registerReq.getStationName())
                .ifPresent(x ->{
                    throw new CustomException(ErrorCode.STATION_ALREADY_EXISTS);
                });

        Region region = regionRepository.findById(registerReq.getRegionId())
                .orElseThrow(() -> new CustomException(ErrorCode.REGION_NOT_FOUND));

        Station station = stationRepository.save(Station.builder()
                .name(registerReq.getStationName())
                .location(registerReq.getLocation())
                .status(registerReq.getStatus())
                .description(registerReq.getDescription())
                .user(user)
                .region(region)
                .build()
        );

        // 배터리 자동 생성
        batteryService.autoCreateBattery(station, LocalDate.now());

        return new StationRegisterRes().toDto(station);
    }

    public List<StationSummaryRes> readList(User user) {
        return stationRepository.findAllByUser(user).stream()
                .map(station -> new StationSummaryRes(
                        station.getStationId(),
                        station.getName(),
                        station.getLocation(),
                        station.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public StationRes readDetail(Long stationId, User user) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new CustomException(ErrorCode.STATION_NOT_EXIST));

        // 사용자 소유 확인
        if (!station.getUser().getUserId().equals(user.getUserId())) {
            throw new CustomException(ErrorCode.STATION_IS_NOT_YOURS);
        }

        return new StationRes(
                station.getStationId(),
                station.getName(),
                station.getLocation(),
                station.getCreatedAt(),
                station.getUpdatedAt(),
                station.getStatus(),
                station.getDescription(),
                station.getRegion().getRegionName()
        );
    }

    public StationRes update(Long stationId, User user, StationUpdateReq req) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new CustomException(ErrorCode.STATION_NOT_EXIST));

        // 사용자 소유 확인
        if (!station.getUser().getUserId().equals(user.getUserId())) {
            throw new CustomException(ErrorCode.STATION_IS_NOT_YOURS);
        }

        station.update(req.getName(), req.getDescription(),req.getStatus());
        return new StationRes(
                station.getStationId(),
                station.getName(),
                station.getLocation(),
                station.getCreatedAt(),
                station.getUpdatedAt(),
                station.getStatus(),
                station.getDescription(),
                station.getRegion().getRegionName()
        );
    }

    public void delete(Long stationId, User user) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new CustomException(ErrorCode.STATION_NOT_EXIST));

        // 사용자 소유 확인
        if (!station.getUser().getUserId().equals(user.getUserId())) {
            throw new CustomException(ErrorCode.STATION_IS_NOT_YOURS);
        }

        stationRepository.delete(station);
    }
}
