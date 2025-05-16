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
    ADMIN_NOT_FOUND(HttpStatus.BAD_REQUEST, "삭제하려는 관리자를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
