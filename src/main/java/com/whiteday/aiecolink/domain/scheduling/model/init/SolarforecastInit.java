package com.whiteday.aiecolink.domain.scheduling.model.init;

import com.whiteday.aiecolink.domain.scheduling.model.entity.SolarforecastHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.SolarforecastPlan;
import com.whiteday.aiecolink.domain.scheduling.repository.SolarforecastPlanRepository;
import com.whiteday.aiecolink.domain.station.model.entity.Region;
import com.whiteday.aiecolink.domain.station.repository.RegionRepository;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SolarforecastInit implements CommandLineRunner {
    private final SolarforecastPlanRepository solarforecastPlanRepository;
    private final RegionRepository regionRepository;
    @Override
    public void run(String... args) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        if (solarforecastPlanRepository.findByForecastDate(yesterday).isPresent()) {
            return;
        }
        Region region = regionRepository.findByRegionId(35L)
                .orElseThrow(() -> new CustomException(ErrorCode.REGION_NOT_FOUND));

        SolarforecastPlan solarforecastPlan = SolarforecastPlan.builder()
                .forecastDate(yesterday)
                .region(region)
                .hourlyList(new ArrayList<>())  // 초기화
                .build();

        for(int hour = 0; hour < 24; hour++) {
            SolarforecastHourly hourly = SolarforecastHourly.builder()
                    .hour(hour)
                    .instantGeneration(0.0F)
                    .solarBattery(0.0F)
                    .build();
            solarforecastPlan.addHourly(hourly); // 양방향 연관관계 자동 주입
        }

        solarforecastPlanRepository.save(solarforecastPlan);
    }

}
