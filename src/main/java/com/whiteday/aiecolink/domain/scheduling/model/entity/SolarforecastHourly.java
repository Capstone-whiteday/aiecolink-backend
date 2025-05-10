package com.whiteday.aiecolink.domain.scheduling.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SolarforecastHourly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solarforecast_id")
    private SolarforecastPlan solarforecastPlan;

    @Column(nullable = false)
    private LocalDate forecastDate;

    @Column
    private int time;
    @Column
    private float instantGeneration;
    @Column
    private float solarBattery;
    @Column
    private float output;
}
