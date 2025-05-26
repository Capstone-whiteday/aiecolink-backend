//package com.whiteday.aiecolink.domain.scheduling.model.init;
//
//import com.whiteday.aiecolink.domain.scheduling.model.entity.TouHourly;
//import com.whiteday.aiecolink.domain.scheduling.model.entity.TouPlan;
//import com.whiteday.aiecolink.domain.scheduling.repository.TouPlanReposiotry;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Random;
//
//@Component
//@RequiredArgsConstructor
//public class TouInit implements CommandLineRunner {
//    private final TouPlanReposiotry touPlanRepository;
//
//    @Override
//    public void run(String... args) {
//        LocalDate today = LocalDate.now();
//        if (touPlanRepository.findByForecastDate(today).isPresent()) {
//            return;
//        }
//
//        TouPlan touPlan = TouPlan.builder()
//                .forecastDate(today)
//                .hourlyList(new ArrayList<>())  // 초기화
//                .build();
//
//        Random random = new Random();
//
//        for (int i = 0; i < 24; i++) {
//            float priceDayAhead = 105f + random.nextFloat() * (117f - 105f); // 105 ~ 117
//            float priceActual = 105f + random.nextFloat() * (117f - 105f); // 105 ~ 117
//            float loadForecast = 45f + random.nextFloat() * (54.5f - 45f); // 45 ~ 54.5
//            float loadActual = 45f + random.nextFloat() * (54.5f - 45f); // 45 ~ 54.5
//
//            TouHourly hourly = TouHourly.builder()
//                    .hour(i)
//                    .priceActual(priceActual)
//                    .priceDayAhead(priceDayAhead)
//                    .totalLoadActual(loadActual)
//                    .totalLoadForecast(loadForecast)
//                    .build();
//
//            touPlan.addHourly(hourly); // 양방향 연관관계 자동 주입
//        }
//
//        touPlanRepository.save(touPlan);
//    }
//
//}
