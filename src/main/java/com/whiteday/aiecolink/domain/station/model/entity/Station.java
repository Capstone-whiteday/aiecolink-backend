package com.whiteday.aiecolink.domain.station.model.entity;

import com.whiteday.aiecolink.domain.user.model.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updatedAt;
}
