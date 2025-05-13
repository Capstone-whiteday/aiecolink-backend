package com.whiteday.aiecolink.domain.scheduling.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TouHourly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tou_id")
    private TouPlan touPlan;

    @Column(nullable = false)
    private int hour;

    @Column(nullable = false)
    private float totalLoadForecast;

    @Column(nullable = false)
    private float totalLoadActual;

    @Column(nullable = false)
    private float priceDayAhead;

    @Column(nullable = false)
    private float priceActual;

}
