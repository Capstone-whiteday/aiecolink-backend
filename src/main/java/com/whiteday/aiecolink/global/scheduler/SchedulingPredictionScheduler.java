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

    // TODO : 해당 시간에 맞춘 자동 예측 (초/분/시/일/월/요일)
    @Scheduled(cron = "0 27 0 * * *", zone = "Asia/Seoul")
    public void scheduledPrediction() {
        log.info("⏰ 발표용 자동 예측 스케줄 실행 시작");
        schedulingService.autoPredictAllStations();
    }
}
