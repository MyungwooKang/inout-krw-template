package com.example.inoutkrwtemplate.controller.template;

import com.example.inoutkrwtemplate.controller.code.ErrorCode;
import lombok.Data;

@Data
public class ApiError {
    private final int code;
    private final String message;
    private final String details;

    public ApiError(int code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
        this.details = null;
    }

    public ApiError(ErrorCode errorCode, String details) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.details = details;
    }

    public ApiError(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.details = null;
    }
}
