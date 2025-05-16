package com.whiteday.aiecolink.domain.scheduling.service;


import com.whiteday.aiecolink.domain.scheduling.controller.SchedulingDashboardRes;
import com.whiteday.aiecolink.domain.scheduling.model.entity.SchedulingHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.SchedulingPlan;
import com.whiteday.aiecolink.domain.scheduling.model.request.PredictionReq;
import com.whiteday.aiecolink.domain.scheduling.model.response.HourlyScheduleDto;
import com.whiteday.aiecolink.domain.scheduling.model.response.SchedulingRes;
import com.whiteday.aiecolink.domain.scheduling.repository.SchedulingHourlyRepository;
import com.whiteday.aiecolink.domain.scheduling.repository.SchedulingPlanRepository;
import com.whiteday.aiecolink.domain.station.model.entity.Station;
import com.whiteday.aiecolink.domain.station.repository.StationRepository;
import com.whiteday.aiecolink.global.client.AiModelClient;
import com.whiteday.aiecolink.global.client.SchedulePredictionItem;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulingService {
    final StationRepository stationRepository;
    final SchedulingPlanRepository schedulingPlanRepository;
    final SchedulingHourlyRepository schedulingHourlyRepository;
    final AiModelClient aiModelClient;

    // 수동 다음날 충/방전 스케줄링 요청 처리
    public SchedulingRes predictSchedule(Long stationId, PredictionReq request) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new CustomException(ErrorCode.STATION_NOT_EXIST));

        LocalDate forecastDate = request.getDate();
        // TODO : AI 모델 예측 호출, return 수정
        // List<ScheduleItem> schedule = aiModelClient.predict(dto.getDate(), station);
        return new SchedulingRes("예측 결과를 성공적으로 가져왔습니다.");
    }

    // 예측 결과 저장
    @Transactional
    public void savePredictionResult(Long stationId, LocalDate forecastDate, List<SchedulePredictionItem> items) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new CustomException(ErrorCode.STATION_NOT_EXIST));

        SchedulingPlan plan = schedulingPlanRepository.save(SchedulingPlan.builder()
                .station(station)
                .forecastDate(forecastDate)
                .build());

        for (SchedulePredictionItem item : items) {
            schedulingHourlyRepository.save(SchedulingHourly.builder()
                    .schedulingPlan(plan)
                    .hour(item.getHour())
                    .predictSolar(item.getPredictSolar())
                    .powerKw(item.getPowerKw())
                    .powerPayment(item.getPowerPayment())
                    .action(item.getAction())
                    .build());
        }
    }

    public SchedulingDashboardRes getDashboard(Long stationId) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new CustomException(ErrorCode.STATION_NOT_EXIST));

        SchedulingPlan plan = (SchedulingPlan) schedulingPlanRepository.findTopByStationOrderByForecastDateDesc(station)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        List<SchedulingHourly> hourlies = schedulingHourlyRepository.findBySchedulingPlan(plan);

        List<HourlyScheduleDto> scheduleList = hourlies.stream()
                .map(h -> HourlyScheduleDto.builder()
                        .hour(h.getHour())
                        .predictSolar(h.getPredictSolar())
                        .powerKw(h.getPowerKw())
                        .powerPayment(h.getPowerPayment())
                        .action(h.getAction())
                        .build())
                .toList();

        float totalCost = (float) hourlies.stream().mapToDouble(SchedulingHourly::getPowerPayment).sum();
        float totalSolar = (float) hourlies.stream().mapToDouble(SchedulingHourly::getPredictSolar).sum();

        return SchedulingDashboardRes.builder()
                .stationId(station.getStationId())
                .forecastDate(plan.getForecastDate())
                .scheduleList(scheduleList)
                .totalCost(totalCost)
                .totalSolar(totalSolar)
                .build();
    }
    // 자동 충전소 전체 예측
    @Transactional
    public void autoPredictAllStations() {
        LocalDate today = LocalDate.now();  // 오늘 날짜 기준으로 예측
        List<Station> stations = stationRepository.findAll();  // 전체 충전소 조회

        for (Station station : stations) {
            try {
                // AI 서버에 예측 요청

                List<SchedulePredictionItem> predictions = aiModelClient.requestPrediction(
                        station.getStationId(), today
                );

                // 예측 결과 저장
                savePredictionResult(station.getStationId(), today, predictions);

                log.info("✅ 예측 성공: stationId={}, date={}", station.getStationId(), today);

            } catch (Exception e) {
                log.error("❌ 예측 실패: stationId={}, error={}", station.getStationId(), e.getMessage());
            }
        }
    }
}