package com.whiteday.aiecolink.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    STATION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 등록된 충전소입니다."),
    STATION_NOT_EXIST(HttpStatus.BAD_REQUEST, "충전소가 존재하지 않습니다."),
    STATION_IS_NOT_YOURS(HttpStatus.FORBIDDEN, "본인의 충전소만 조회할 수 있습니다."),
    TEST_USER_NOT_EXISTS(HttpStatus.BAD_REQUEST,"테스트 사용자를 찾을 수 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 지역입니다." ),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 이메일입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    NOT_AN_ADMIN(HttpStatus.FORBIDDEN, "관리자 권한이 없습니다."),
    AI_PREDICT_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "AI 모델 예측 요청에 실패했습니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "예측된 스케줄이 존재하지 않습니다."),
    CANNOT_DELETE_SELF(HttpStatus.FORBIDDEN, "자기 자신은 삭제할 수 없습니다."),
    ADMIN_NOT_FOUND(HttpStatus.BAD_REQUEST, "삭제하려는 관리자를 찾을 수 없습니다."),
    SOLARFORECAST_PLAN_NOT_EXIST(HttpStatus.NOT_FOUND, "해당 지역의 태양광 일정이 존재하지 않습니다."),
    SOLARFORECAST_HOURLY_NOT_EXIST(HttpStatus.NOT_FOUND," 해당 지역의 태양광 시간대 데이터기 존재하지 않습니다."),
    TOU_PLAN_NOT_EXIST(HttpStatus.NOT_FOUND, "해당 날짜의 요금제가 존재하지 않습니다."),
    TOU_HOURLY_NOT_EXIST(HttpStatus.NOT_FOUND, "해당 날짜의 요금제 시간대 데이터가 존재하지 않습니다."),
    BATTERY_REGISTER_FAIL(HttpStatus.BAD_REQUEST, "베터리 등록에 실패했습니다."),
    BATTERY_NOT_EXIST(HttpStatus.NOT_FOUND, "베터리가 존재하지 않습니다."),
    BATTERY_UPDATE_FAIL(HttpStatus.BAD_REQUEST, "베터리 수정에 실패했습니다."),
    SCHEDULE_ALREADY_EXIST(HttpStatus.ALREADY_REPORTED, "이미 예측된 스케줄이 존재합니다."),
    INVALID_AI_INPUT(HttpStatus.BAD_REQUEST, "AI 모델 입력 데이터의 길이가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
