package com.whiteday.aiecolink.domain.battery;

import com.whiteday.aiecolink.domain.battery.entity.Battery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BatteryRegisterRes {
    private Long batteryId; // 배터리 ID
    private float capacity; // 배터리 용량 (kWh)

    public BatteryRegisterRes toDto(Battery battery) {
        return BatteryRegisterRes.builder()
                .batteryId(battery.getId())
                .capacity(battery.getBatteryCapacity())
                .build();
    }
}
