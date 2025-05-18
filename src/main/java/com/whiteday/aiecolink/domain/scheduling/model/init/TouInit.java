package com.whiteday.aiecolink.domain.scheduling.model.init;

import com.whiteday.aiecolink.domain.scheduling.model.entity.TouHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.TouPlan;
import com.whiteday.aiecolink.domain.scheduling.repository.TouPlanReposiotry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TouInit implements CommandLineRunner {
    private final TouPlanReposiotry touPlanRepository;

    @Override
    public void run(String... args) {
        LocalDate today = LocalDate.now();
        if (touPlanRepository.findByForecastDate(today).isPresent()) {
            return;
        }

        TouPlan touPlan = TouPlan.builder()
                .forecastDate(today)
                .hourlyList(new ArrayList<>())  // 초기화
                .build();

        for (int i = 0; i < 24; i++) {
            TouHourly hourly = TouHourly.builder()
                    .hour(i)
                    .priceActual(0.0F)
                    .priceDayAhead(0.0F)
                    .totalLoadActual(0.0F)
                    .totalLoadForecast(0.0F)
                    .build();

            touPlan.addHourly(hourly); // 양방향 연관관계 자동 주입
        }

        touPlanRepository.save(touPlan);
    }

}
