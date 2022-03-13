package com.example.inoutkrwtemplate.controller;

import com.example.inoutkrwtemplate.common.exception.ExternalServiceException;
import com.example.inoutkrwtemplate.controller.code.ErrorCode;
import com.example.inoutkrwtemplate.controller.dto.AuthRequest;
import com.example.inoutkrwtemplate.controller.dto.AuthResponse;
import com.example.inoutkrwtemplate.controller.dto.KakaoAuthCallbackRequest;
import com.example.inoutkrwtemplate.controller.template.ApiError;
import com.example.inoutkrwtemplate.controller.template.ApiResponse;
import com.example.inoutkrwtemplate.repository.entity.SettleRequestData;
import com.example.inoutkrwtemplate.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    /**
     * 인증 API
     * @param authRequest
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ApiResponse> sampleAuth(@Valid @RequestBody AuthRequest authRequest) {

        SettleRequestData settleRequestData;

        try {
            settleRequestData = authService.sampleAuth(authRequest.getUsername());
        } catch (ExternalServiceException e) {
            return ResponseEntity.status(404) // error example
                                 .body(new ApiResponse(new ApiError(ErrorCode.BADREQUEST_1)));
        }

        AuthResponse authResponse = new AuthResponse();
        authResponse.setSampleField(settleRequestData.getUserName());

        return ResponseEntity.ok(new ApiResponse(authResponse));
    }

    /**
     * 세틀뱅크에서 호출하는 callback api
     * @param kakaoAuthCallbackRequest
     */
    @PostMapping("/auth/kakao/callback")
    public void kakaoAuthCallback(KakaoAuthCallbackRequest kakaoAuthCallbackRequest) {
        authService.kakaoAuthCallback();
    }


    /**
     * for jpql test
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ApiResponse> sampleGet() {
        return ResponseEntity.ok(new ApiResponse(authService.findTest()));
    }
}
