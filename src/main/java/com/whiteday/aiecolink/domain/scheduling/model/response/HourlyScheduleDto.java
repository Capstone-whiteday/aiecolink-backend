package com.whiteday.aiecolink.domain.scheduling.model.response;

import com.whiteday.aiecolink.domain.scheduling.model.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HourlyScheduleDto {
    private int hour;
    private float predictSolar;
    private float powerKw;
    private float powerPayment;
    private Action action;
}
