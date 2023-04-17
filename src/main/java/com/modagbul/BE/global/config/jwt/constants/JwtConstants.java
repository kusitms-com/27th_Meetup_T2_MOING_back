package com.modagbul.BE.global.config.jwt.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class JwtConstants {
    @Getter
    @RequiredArgsConstructor
    public enum     JWTExceptionList {
        UNKNOWN_ERROR("J0001", HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 오류가 발생했습니다."),
        MAL_FORMED_TOKEN("J0002", HttpStatus.UNAUTHORIZED,"잘못된 JWT 서명입니다."),
        EXPIRED_TOKEN("J0003", HttpStatus.UNAUTHORIZED,"만료된 토큰입니다."),
        UNSUPPORTED_TOKEN("J0004", HttpStatus.UNAUTHORIZED, "지원되지 않는 토큰입니다."),
        ACCESS_DENIED("J0005", HttpStatus.UNAUTHORIZED,"접근이 거부되었습니다."),
        ILLEGAL_TOKEN("J0006", HttpStatus.UNAUTHORIZED,"JWT 토큰이 잘못되었습니다.");

        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;

    }
}
