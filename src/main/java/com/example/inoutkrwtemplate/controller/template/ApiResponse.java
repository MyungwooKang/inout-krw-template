package com.example.inoutkrwtemplate.controller.template;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ApiError error;

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
        this.error = null;
    }

    public ApiResponse(ApiError error) {
        this.success = false;
        this.data = null;
        this.error = error;
    }
}
