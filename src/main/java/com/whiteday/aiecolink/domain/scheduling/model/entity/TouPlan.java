package com.whiteday.aiecolink.domain.scheduling.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TouPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long touId;

    private LocalDate forecastDate;

    @OneToMany(mappedBy = "touPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TouHourly> hourlyList;

    public void addHourly(TouHourly hourly) {
        this.hourlyList.add(hourly);
        hourly.setTouPlan(this);
    }

}
