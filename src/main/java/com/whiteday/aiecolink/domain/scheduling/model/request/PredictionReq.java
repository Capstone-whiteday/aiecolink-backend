package com.whiteday.aiecolink.domain.scheduling.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionReq {
    private LocalDate date;    // 요청 날짜 (예: "2025-10-01")
}