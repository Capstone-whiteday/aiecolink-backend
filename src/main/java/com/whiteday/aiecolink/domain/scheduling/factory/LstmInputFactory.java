package com.whiteday.aiecolink.domain.scheduling.factory;

import com.whiteday.aiecolink.domain.scheduling.model.entity.SolarforecastHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.SolarforecastPlan;
import com.whiteday.aiecolink.domain.scheduling.model.request.LstmInput;
import com.whiteday.aiecolink.domain.scheduling.repository.SolarforecastHourlyRepository;
import com.whiteday.aiecolink.domain.scheduling.repository.SolarforecastPlanRepository;
import com.whiteday.aiecolink.domain.station.model.entity.Station;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LstmInputFactory {
   final SolarforecastPlanRepository solarforecastPlanRepository;
   final SolarforecastHourlyRepository solarforecastHourlyRepository;
    public List<LstmInput> createLstmInput(Station station, LocalDate date) {

        Long regionId = station.getRegion().getRegionId();
        SolarforecastPlan yesterdayPlan = solarforecastPlanRepository
                .findByRegion_RegionIdAndForecastDate(regionId, date.minusDays(1))
                .orElseThrow(() -> new CustomException(ErrorCode.SOLARFORECAST_PLAN_NOT_EXIST));

        List<SolarforecastHourly> hourlyList = solarforecastHourlyRepository.findBySolarforecastPlan_SolarforecastId(yesterdayPlan.getSolarforecastId());
        if (hourlyList.isEmpty()) {
            throw new CustomException(ErrorCode.SOLARFORECAST_HOURLY_NOT_EXIST);
        }

        Map<Integer, SolarforecastHourly> hourlyMap = hourlyList.stream()
                .collect(Collectors.toMap(SolarforecastHourly::getHour, h -> h));

        List<LstmInput> inputs = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            SolarforecastHourly hourly = hourlyMap.get(hour);
            if (hourly == null) {
                throw new CustomException(ErrorCode.SOLARFORECAST_HOURLY_NOT_EXIST,
                        hour + "시의 데이터가 누락되었습니다.");
            }
            inputs.add(LstmInput.builder()
                    .hour(hour)
                    .region(Math.toIntExact(regionId))
                    .solarBattery_kW(hourly.getSolarBattery())
                    .instantaneous_generation_kW(hourly.getInstantGeneration())
                    .build());
        }
        return inputs;
    }
}

