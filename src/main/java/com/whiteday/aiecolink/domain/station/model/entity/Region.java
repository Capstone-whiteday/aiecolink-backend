package com.whiteday.aiecolink.domain.station.model.entity;

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
@Table(name = "regions")
public class Region {
    @Id
    private Long regionId;

    @Column(nullable = false, length = 30)
    private String regionName;

}
