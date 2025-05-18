package com.whiteday.aiecolink.domain.scheduling.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SolarforecastHourly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solarforecast_id")
    private SolarforecastPlan solarforecastPlan;

    @Column(nullable = false)
    private int hour;
    @Column(nullable = false)
    private float instantGeneration;
    @Column(nullable = false)
    private float solarBattery;

    public void setSolarforecastPlan(SolarforecastPlan solarforecastPlan) {
        this.solarforecastPlan = solarforecastPlan;
    }
}
