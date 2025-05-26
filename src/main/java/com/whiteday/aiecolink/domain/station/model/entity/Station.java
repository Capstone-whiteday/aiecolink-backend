package com.whiteday.aiecolink.domain.station.model.entity;

import com.whiteday.aiecolink.domain.battery.entity.Battery;
import com.whiteday.aiecolink.domain.scheduling.model.entity.SchedulingPlan;
import com.whiteday.aiecolink.domain.station.model.Status;
import com.whiteday.aiecolink.domain.member.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(nullable = false,length = 50)
    private String location;

    @Column(nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column
    private String description;

    @OneToMany(mappedBy = "station",fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<SchedulingPlan> schedulingPlanList;

    @OneToMany(mappedBy = "station",fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Battery> batteryList;

    public void update(String name, String description,Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

}
