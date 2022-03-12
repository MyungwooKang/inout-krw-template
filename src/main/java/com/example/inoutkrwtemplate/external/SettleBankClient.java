package com.example.inoutkrwtemplate.external;

import com.example.inoutkrwtemplate.common.config.FeignConfig;
import com.example.inoutkrwtemplate.external.dto.SettleBankKakaoAuthRequest;
import com.example.inoutkrwtemplate.external.dto.SettleBankKakaoAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "settle-bank-client", url = "${config.clients.settle-bank.base-url}", primary = false ,configuration = FeignConfig.class)
public interface SettleBankClient {

    //TODO settle-bank 인증 요청 api 스펙에 맞춰 수정
    @PostMapping(value = "/settle-kakao-auth-endpoint")
    SettleBankKakaoAuthResponse kakaoAuth(@RequestBody SettleBankKakaoAuthRequest request);
}
