package com.whiteday.aiecolink.domain.scheduling.model.entity;

import com.whiteday.aiecolink.domain.scheduling.model.Action;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SchedulingHourly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduling_id")
    private SchedulingPlan schedulingPlan;

    @Column
    private int time;
    @Column
    private float predictSolar;
    @Column
    private float powerKw;
    @Column
    private float powerPayment;
    @Column
    private Action action;
}
