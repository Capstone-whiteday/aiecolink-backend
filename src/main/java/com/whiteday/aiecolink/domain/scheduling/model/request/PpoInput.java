package com.whiteday.aiecolink.domain.scheduling.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PpoInput {
    private int hour;
    private float price_actual;
    private float price_day_ahead;
    private float total_load_actual;
    private float total_load_forecast;

}
