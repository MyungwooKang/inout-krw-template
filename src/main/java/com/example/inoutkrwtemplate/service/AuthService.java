package com.example.inoutkrwtemplate.service;

import com.example.inoutkrwtemplate.common.exception.ExternalServiceException;
import com.example.inoutkrwtemplate.repository.entity.SettleRequestData;
import com.example.inoutkrwtemplate.repository.vo.SampleSpecialColumnSelectVo;

import java.util.List;

public interface AuthService {
    /**
     * 사용자 인증 요청
     * @return
     */
    SettleRequestData sampleAuth(String userName) throws ExternalServiceException;

    /**
     * 카카오 인증 callback 처리
     */
    void kakaoAuthCallback();

    List<SampleSpecialColumnSelectVo> findTest();
}
