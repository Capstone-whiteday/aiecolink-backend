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
public class PpoInput {
    private int hour;

    @JsonProperty("price actual")
    private float priceActual;

    @JsonProperty("price day ahead")
    private float priceDayAhead;

    @JsonProperty("total load forecast")
    private float totalLoadForecast;

    @JsonProperty("total load actual")
    private float totalLoadActual;

}
