package com.whiteday.aiecolink.domain.scheduling.factory;

import com.whiteday.aiecolink.domain.scheduling.model.entity.SolarforecastHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.TouHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.TouPlan;
import com.whiteday.aiecolink.domain.scheduling.model.request.PpoInput;
import com.whiteday.aiecolink.domain.scheduling.repository.TouHourlyRepository;
import com.whiteday.aiecolink.domain.scheduling.repository.TouPlanReposiotry;
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

@RequiredArgsConstructor
@Component
public class PpoInputFactory {
    final TouPlanReposiotry touPlanReposiotry;
    final TouHourlyRepository touHourlyRepository;
    public List<PpoInput> createPpoInput(LocalDate date) {
        TouPlan touPlan = touPlanReposiotry
                .findByForecastDate(date)
                .orElseThrow(() -> new CustomException(ErrorCode.TOU_PLAN_NOT_EXIST));
        List<TouHourly> hourlyList = touHourlyRepository.findByTouPlan_TouId(touPlan.getTouId());
        if (hourlyList.isEmpty()) {
            throw new CustomException(ErrorCode.TOU_HOURLY_NOT_EXIST);
        }

        Map<Integer, TouHourly> hourlyMap = hourlyList.stream()
                .collect(Collectors.toMap(TouHourly::getHour, h -> h));

        List<PpoInput> inputs = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            TouHourly hourly = hourlyMap.get(hour);
            if (hourly == null) {
                throw new CustomException(ErrorCode.TOU_HOURLY_NOT_EXIST,
                        hour + "시의 데이터가 누락되었습니다.");
            }
            inputs.add(PpoInput.builder()
                    .hour(hour)
                    .priceActual(hourly.getPriceActual())
                    .priceDayAhead(hourly.getPriceDayAhead())
                    .totalLoadActual(hourly.getTotalLoadActual())
                    .totalLoadForecast(hourly.getTotalLoadForecast())
                    .build());
        }
        return inputs;
    }
}
