package com.whiteday.aiecolink.domain.scheduling.service;


import com.whiteday.aiecolink.domain.battery.repository.BatteryRepository;
import com.whiteday.aiecolink.domain.scheduling.factory.LstmInputFactory;
import com.whiteday.aiecolink.domain.scheduling.factory.PpoInputFactory;
import com.whiteday.aiecolink.domain.scheduling.model.request.LstmInput;
import com.whiteday.aiecolink.domain.scheduling.model.request.PpoInput;
import com.whiteday.aiecolink.domain.scheduling.model.response.SchedulingDashboardRes;
import com.whiteday.aiecolink.domain.scheduling.model.entity.SchedulingHourly;
import com.whiteday.aiecolink.domain.scheduling.model.entity.SchedulingPlan;
import com.whiteday.aiecolink.domain.scheduling.model.request.PredictionRequest;
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
    final BatteryRepository batteryRepository;
    final BatteryService batteryService;
    final AiModelClient aiModelClient;
    final LstmInputFactory lstmInputFactory;
    final PpoInputFactory ppoInputFactory;

    // 수동 다음날 충/방전 스케줄링 요청 처리
    public SchedulingRes predictSchedule(Long stationId, PredictionRequest request) {
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

        SchedulingPlan plan = schedulingPlanRepository.findTopByStationOrderByForecastDateDesc(station)
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

        List<Station> stations = stationRepository.findAll().stream()
                .filter(station -> !schedulingPlanRepository.existsByStationAndForecastDate(station, today))
                .toList();

        if (stations.isEmpty()) {
            log.info("❌ 모든 충전소에 대해 오늘 날짜의 예측이 이미 존재합니다.");
            return;
        }
        log.info("🔄 예측이 없는 충전소 수: {}", stations.size());
        for (Station station : stations) {
            try {
                log.info("🔄 시작: stationId={}, date={}", station.getStationId(), today);

                // 🔹 0. 배터리 상태 확인
                if (!batteryService.isBatteryAvailable(station, today)) {
                    log.error("❌ 배터리 상태 불량: stationId={}, date={}", station.getStationId(), today);
                    continue;
                }
                log.info("✅ 배터리 상태 확인 완료: stationId={}, date={}", station.getStationId(), today);

                // 🔹 1. LSTM/PPO 입력 생성
                log.info("⏳ LSTM 입력 생성 시작");
                List<LstmInput> lstmInputs = lstmInputFactory.createLstmInput(station, today);
                log.info("✅ LSTM 입력 생성 완료");

                log.info("⏳ PPO 입력 생성 시작");
                List<PpoInput> ppoInputs = ppoInputFactory.createPpoInput(today);
                log.info("✅ PPO 입력 생성 완료");

                // 1-1. batteryCapacity 설정
                log.info("🔋 배터리 용량 조회 시작");
                float batteryCapacity = batteryRepository.findByStationAndDate(station, today)
                        .orElseThrow(() -> new CustomException(ErrorCode.BATTERY_NOT_EXIST))
                        .getBatteryCapacity();
                log.info("✅ 배터리 용량 조회 완료: batteryCapacity={}", batteryCapacity);

                // 🔹 2. AI 서버에 예측 요청
                log.info("📡 AI 예측 요청 시작");
                List<SchedulePredictionItem> predictions = aiModelClient.requestPrediction(
                        batteryCapacity,
                        lstmInputs,
                        ppoInputs
                );
                log.info("✅ AI 예측 응답 완료: 예측 수 = {}", predictions.size());

                // 🔹 3. 결과 저장
                log.info("💾 예측 결과 저장 시작");
                savePredictionResult(station.getStationId(), today, predictions);
                log.info("✅ 예측 결과 저장 완료");

                log.info("🎉 전체 성공: stationId={}, date={}", station.getStationId(), today);

            } catch (Exception e) {
                log.error("❌ 예측 실패: stationId={}, date={}, error={}", station.getStationId(), today, e.getMessage(), e);
            }
        }

    }
}