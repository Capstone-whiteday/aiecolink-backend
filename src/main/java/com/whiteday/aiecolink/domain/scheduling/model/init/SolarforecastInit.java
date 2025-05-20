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
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Order(2)
@Component
@RequiredArgsConstructor
public class SolarforecastInit implements CommandLineRunner {
    private final SolarforecastPlanRepository solarforecastPlanRepository;
    private final RegionRepository regionRepository;
    @Override
    public void run(String... args) {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        // 전체 지역에 대해 예측 데이터 생성
        for(Region region : regionRepository.findAll()) {
            // 이미 예측 데이터가 존재하는 경우 건너뜀
            if (solarforecastPlanRepository.findByRegion_RegionIdAndForecastDate(region.getRegionId(), yesterday).isPresent()) {
                continue;
            }
            createForecastData(region, yesterday);
        }
    }

    private void createForecastData(Region region, LocalDate yesterday) {
        SolarforecastPlan solarforecastPlan = SolarforecastPlan.builder()
                .forecastDate(yesterday)
                .region(region)
                .hourlyList(new ArrayList<>())  // 초기화
                .build();

        Random random = new Random();

        for(int hour = 0; hour < 24; hour++) {
            float generation = 0.2f + random.nextFloat() * (0.35f - 0.2f); // 0.2 ~ 0.35
            float battery = 1.0f + random.nextFloat() * (1.4f - 1.0f); // 1.0 ~ 1.4
            SolarforecastHourly hourly = SolarforecastHourly.builder()
                    .hour(hour)
                    .instantGeneration(generation)
                    .solarBattery(battery)
                    .build();
            solarforecastPlan.addHourly(hourly); // 양방향 연관관계 자동 주입
        }

        solarforecastPlanRepository.save(solarforecastPlan);
    }

}
