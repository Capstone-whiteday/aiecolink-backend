package com.whiteday.aiecolink.domain.station.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegionResponseDto {

    @JsonProperty("id")  // 프론트에서 요구하는 JSON 키 이름
    private Long regionId;

    @JsonProperty("name")  // 프론트에서 요구하는 JSON 키 이름
    private String regionName;
}