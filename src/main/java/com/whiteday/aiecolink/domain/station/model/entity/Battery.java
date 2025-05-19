package com.whiteday.aiecolink.domain.station.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @Column(nullable = false)
    private float batteryCapacity; // 배터리 용량 (kWh)

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate date;

    public void update(float batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        this.date = LocalDate.now();
    }
}
