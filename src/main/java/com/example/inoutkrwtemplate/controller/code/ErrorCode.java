package com.example.inoutkrwtemplate.controller.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BADREQUEST_1(40001, "...는 잘못된 요청입니다"),
    //...
    NOTFOUND_1(40401, "...를 찾을 수 없습니다");
    //...
    ///TODO...

    private int code;
    private String message;
}
