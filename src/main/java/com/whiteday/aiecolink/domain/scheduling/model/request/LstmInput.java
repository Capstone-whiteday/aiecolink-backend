package com.whiteday.aiecolink.domain.scheduling.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LstmInput {
    private int hour;
    private int region;
    private float solarBattery_kW;
    private float instantaneous_generation_kW;
    // + 필요한 특성들 추가 (예: float feature1, feature2 등)
}
