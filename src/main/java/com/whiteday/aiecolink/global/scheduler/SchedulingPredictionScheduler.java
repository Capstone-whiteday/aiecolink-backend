package com.whiteday.aiecolink.global.scheduler;

import com.whiteday.aiecolink.domain.scheduling.service.SchedulingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulingPredictionScheduler {
    private final SchedulingService schedulingService;

    // TODO : 발표 시간에 맞춘 자동 예측 (예: 오후 1시 40분)
    @Scheduled(cron = "0 36 18 * * *", zone = "Asia/Seoul")  // ← 발표 시간에 맞게 수정
    public void scheduledPrediction() {
        log.info("⏰ 발표용 자동 예측 스케줄 실행 시작");
        schedulingService.autoPredictAllStations();
    }
}
