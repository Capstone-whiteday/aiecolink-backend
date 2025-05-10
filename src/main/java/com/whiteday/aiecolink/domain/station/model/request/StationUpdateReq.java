package com.whiteday.aiecolink.domain.station.model.request;

import com.whiteday.aiecolink.domain.station.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StationUpdateReq {
    private String name;
    private Status status;
    private String description;
}
