package com.whiteday.aiecolink.domain.scheduling.controller;
import com.whiteday.aiecolink.domain.scheduling.model.request.PredictionRequest;
import com.whiteday.aiecolink.domain.scheduling.model.response.SchedulingDashboardRes;
import com.whiteday.aiecolink.domain.scheduling.model.response.SchedulingRes;
import com.whiteday.aiecolink.domain.scheduling.service.SchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    // 다음날 충/방전 스케줄링 요청
    @PostMapping("/{stationId}/predict")
    public ResponseEntity<SchedulingRes> predictSchedule(
            @PathVariable Long stationId,
            @RequestBody PredictionRequest request) {
        return ResponseEntity.ok(schedulingService.predictSchedule(stationId, request));
    }

    // AI 분석 결과 스케줄링 대시보드 조회
    @GetMapping("/dashboard/{stationId}")
    public ResponseEntity<SchedulingDashboardRes> getDashboard(@PathVariable Long stationId) {
        SchedulingDashboardRes response = schedulingService.getDashboard(stationId);
        return ResponseEntity.ok(response);
    }
}