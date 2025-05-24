package com.whiteday.aiecolink.domain.scheduling.model.entity;

import com.whiteday.aiecolink.domain.station.model.entity.Station;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SchedulingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedulingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @Column(nullable = false)
    private LocalDate forecastDate;

    @Column(nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    private float totalCost;

    @Column
    private float savingCost;

    @OneToMany(mappedBy = "schedulingPlan",fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<SchedulingHourly> schedulingHourlyList;

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void setSavingCost(float savingCost) {
        this.savingCost = savingCost;
    }
}
