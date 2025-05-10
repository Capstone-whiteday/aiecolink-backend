package com.whiteday.aiecolink.domain.scheduling.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
}
