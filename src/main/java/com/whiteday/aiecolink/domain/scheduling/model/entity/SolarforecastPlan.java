package com.whiteday.aiecolink.domain.scheduling.model.entity;

import com.whiteday.aiecolink.domain.station.model.entity.Region;
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
public class SolarforecastPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solarforecastId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(nullable = false)
    private LocalDate forecastDate;
}