package com.whiteday.aiecolink.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    STATION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 등록된 충전소입니다.");

    private final HttpStatus status;
    private final String message;

}
