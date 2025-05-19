package com.whiteday.aiecolink.domain.scheduling.service;

import com.whiteday.aiecolink.domain.battery.BatteryRegisterRes;
import com.whiteday.aiecolink.domain.battery.repository.BatteryRepository;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import com.whiteday.aiecolink.domain.station.model.entity.Battery;
import com.whiteday.aiecolink.domain.station.model.entity.Station;
import com.whiteday.aiecolink.domain.station.repository.StationRepository;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class BatteryService {
    private final StationRepository stationRepository;
    private final BatteryRepository batteryRepository;
    // 베터리 등록
    public BatteryRegisterRes register(Long stationId, User user, float batteryCapacity) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new CustomException(ErrorCode.STATION_NOT_EXIST));

        // 사용자 소유 확인
        if (!station.getUser().getUserId().equals(user.getUserId())) {
            throw new CustomException(ErrorCode.STATION_IS_NOT_YOURS);
        }
        Battery battery;
        try {
            // 베터리 등록
            battery = batteryRepository.save(Battery.builder()
                    .station(station)
                    .batteryCapacity(batteryCapacity)
                    .date(LocalDate.now()) // 베터리 등록하는 시점이 해당 날짜
                    .build()
            );
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BATTERY_REGISTER_FAIL);
        }
        return new BatteryRegisterRes().toDto(battery);
    }

    public BatteryRegisterRes read(Long batteryId, User user) {
        Battery battery = batteryRepository.findById(batteryId)
                .orElseThrow(() -> new CustomException(ErrorCode.BATTERY_NOT_EXIST));
        Station station = battery.getStation();
        // 사용자 소유 확인
        if (!station.getUser().getUserId().equals(user.getUserId())) {
            throw new CustomException(ErrorCode.STATION_IS_NOT_YOURS);
        }
        return new BatteryRegisterRes().toDto(battery);
    }

    public BatteryRegisterRes update(Long batteryId, User user, float batteryCapacity) {
        Battery battery = batteryRepository.findById(batteryId)
                .orElseThrow(() -> new CustomException(ErrorCode.BATTERY_NOT_EXIST));
        Station station = battery.getStation();
        // 사용자 소유 확인
        if (!station.getUser().getUserId().equals(user.getUserId())) {
            throw new CustomException(ErrorCode.STATION_IS_NOT_YOURS);
        }
        // 배터리 용량 수정
        try {
            battery.update(batteryCapacity);
            batteryRepository.save(battery);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BATTERY_UPDATE_FAIL);
        }
        return new BatteryRegisterRes().toDto(battery);
    }

    public boolean isBatteryAvailable(Station station, LocalDate today) {
        // 배터리 상태 확인
        Battery battery = batteryRepository.findByStationAndDate(station, today)
                .orElseThrow(() -> new CustomException(ErrorCode.BATTERY_NOT_EXIST));
        return battery.getBatteryCapacity() > 0;
    }
}
