package com.whiteday.aiecolink.global.client;

import lombok.Data;

import java.util.List;

@Data
public class SchedulePredictionRes {
    private List<SchedulePredictionItem> result;
}
