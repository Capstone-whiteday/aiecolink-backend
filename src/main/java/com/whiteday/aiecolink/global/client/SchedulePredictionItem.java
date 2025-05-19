package com.whiteday.aiecolink.global.client;

import com.whiteday.aiecolink.domain.scheduling.model.Action;
import lombok.Getter;

@Getter
public class SchedulePredictionItem { // AI 응답 데이터 구조 (AI -> 백엔드 )
    private int hour; // 0 ~ 23
    private float predictSolar;
    private float powerKw;
    private float powerPayment;
    private String action; // CHARGE, DISCHARGE, IDLE
}
