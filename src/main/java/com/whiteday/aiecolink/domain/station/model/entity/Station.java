package com.whiteday.aiecolink.domain.station.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long stationId;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String location;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updatedAt;
}
