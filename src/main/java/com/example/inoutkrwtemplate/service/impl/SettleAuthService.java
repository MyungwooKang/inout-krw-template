package com.example.inoutkrwtemplate.service.impl;

import com.example.inoutkrwtemplate.common.exception.ExternalServiceException;
import com.example.inoutkrwtemplate.external.SettleBankClient;
import com.example.inoutkrwtemplate.external.dto.SettleBankKakaoAuthRequest;
import com.example.inoutkrwtemplate.external.dto.SettleBankKakaoAuthResponse;
import com.example.inoutkrwtemplate.repository.SettleRequestDataRepository;
import com.example.inoutkrwtemplate.repository.entity.SettleRequestData;
import com.example.inoutkrwtemplate.repository.enums.ReceiveFlag;
import com.example.inoutkrwtemplate.repository.enums.SendFlag;
import com.example.inoutkrwtemplate.repository.vo.SampleSpecialColumnSelectVo;
import com.example.inoutkrwtemplate.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SettleAuthService implements AuthService {

    private final SettleRequestDataRepository settleRequestDataRepository;
    private final SettleBankClient settleBankClient;

    @Override
    public SettleRequestData sampleAuth(String userName) throws ExternalServiceException {
        SettleRequestData newSettleRequestData = SettleRequestData.builder()
                                                                  .sendFlag(SendFlag.INPROGRESS)
                                                                  .userName(userName)
                                                                  .build();

        SettleBankKakaoAuthResponse settleBankKakaoAuthResponse;

        log.info("요청 내역 저장 : {}", newSettleRequestData);
        newSettleRequestData = settleRequestDataRepository.save(newSettleRequestData);

        try {
            log.info("세틀뱅크 인증 요청 API 실행");
            settleBankKakaoAuthResponse = settleKakaoAuth();
            log.debug("인증요청 완료. {}", settleBankKakaoAuthResponse);
        } catch (Exception e) {
            newSettleRequestData.setReceiveFlag(ReceiveFlag.FAILED);
            settleRequestDataRepository.save(newSettleRequestData);
            throw new ExternalServiceException("에러 ex");
        }

        newSettleRequestData.setSendFlag(SendFlag.DONE);
        log.info("전송 완료 내역 저장. {}", newSettleRequestData);
        return settleRequestDataRepository.save(newSettleRequestData);
    }

    private SettleBankKakaoAuthResponse settleKakaoAuth() throws Exception {
        SettleBankKakaoAuthRequest settleBankKakaoAuthRequest = new SettleBankKakaoAuthRequest();

        //TODO 런타임 테스트 하고싶으면 아래 주석 처리 후 61~63번 라인 주석해제해서 사용.
        return settleBankClient.kakaoAuth(settleBankKakaoAuthRequest);

//        SettleBankKakaoAuthResponse settleBankKakaoAuthResponse = new SettleBankKakaoAuthResponse();
//        settleBankKakaoAuthResponse.setSampleField("sample success");
//        return settleBankKakaoAuthResponse;
    }

    @Override
    @Transactional
    public void kakaoAuthCallback() {
        //TODO 인증 콜백 도착시 처리할 부분 작성
    }

    @Override
    public List<SampleSpecialColumnSelectVo> findTest() {
        return settleRequestDataRepository.findProjectionsBySendFlag(SendFlag.DONE);
    }
}
