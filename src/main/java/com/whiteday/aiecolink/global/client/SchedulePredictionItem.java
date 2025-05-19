package com.whiteday.aiecolink.global.client;

import com.whiteday.aiecolink.domain.scheduling.model.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
// AI 응답 데이터 구조 (AI -> 백엔드 )
public class SchedulePredictionItem {
    private int hour; // 0 ~ 23
    private float predictSolar;
    private float powerKw;
    private float powerPayment;
    private Action action; // CHARGE, DISCHARGE, IDLE
}
