package com.whiteday.aiecolink.domain.scheduling.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("solar battery_kW")
    private float solarBatteryKW;

    @JsonProperty("instantaneous_generation_kW")
    private float instantaneousGenerationKW;
    // + 필요한 특성들 추가 (예: float feature1, feature2 등)
}
