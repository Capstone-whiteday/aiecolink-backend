package com.whiteday.aiecolink.domain.scheduling.controller;

import com.whiteday.aiecolink.domain.scheduling.model.response.HourlyScheduleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SchedulingDashboardRes {
    private Long stationId;
    private LocalDate forecastDate;
    private List<HourlyScheduleDto> scheduleList;
    private float totalCost;
    private float totalSolar;
}
