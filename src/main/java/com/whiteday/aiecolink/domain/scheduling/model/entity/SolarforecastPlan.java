package com.whiteday.aiecolink.domain.scheduling.model.entity;

import com.whiteday.aiecolink.domain.station.model.entity.Region;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "solarforecastPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolarforecastHourly> hourlyList;

    public void addHourly(SolarforecastHourly hourly) {
        this.hourlyList.add(hourly);
        hourly.setSolarforecastPlan(this);
    }
}