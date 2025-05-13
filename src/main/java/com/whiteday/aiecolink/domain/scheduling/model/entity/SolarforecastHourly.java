package com.whiteday.aiecolink.domain.scheduling.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column
    private int hour;
    @Column
    private float instantGeneration;
    @Column
    private float solarBattery;

}
