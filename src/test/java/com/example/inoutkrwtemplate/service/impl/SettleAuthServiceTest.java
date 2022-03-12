package com.example.inoutkrwtemplate.service.impl;

import com.example.inoutkrwtemplate.config.TestProperties;
import com.example.inoutkrwtemplate.external.SettleBankClient;
import com.example.inoutkrwtemplate.external.dto.SettleBankKakaoAuthRequest;
import com.example.inoutkrwtemplate.external.dto.SettleBankKakaoAuthResponse;
import com.example.inoutkrwtemplate.repository.SettleRequestDataRepository;
import com.example.inoutkrwtemplate.repository.entity.SettleRequestData;
import com.example.inoutkrwtemplate.repository.enums.ReceiveFlag;
import com.example.inoutkrwtemplate.repository.enums.SendFlag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@SpringBootTest(classes = SettleAuthService.class)
@Import(TestProperties.class)
class SettleAuthServiceTest {
    @MockBean
    private SettleRequestDataRepository mockRepository;

    @MockBean
    private SettleBankClient settleBankClient;

    @Autowired
    private SettleAuthService service;

    @Autowired
    private TestProperties properties;

    @Test
    void sampleAuth_정상처리() throws Exception {
        //given
        String testParam = "test";

        SettleRequestData testPrevVo = SettleRequestData.builder()
                                                    .seq(1L)
                                                    .sendFlag(SendFlag.OPENED)
                                                    .receiveFlag(ReceiveFlag.OPENED)
                                                    .userName(testParam)
                                                    .requestRegDt(new Timestamp((new Date()).getTime()))
                                                    .build();
        SettleRequestData testPostVo = SettleRequestData.builder()
                                                    .seq(1L)
                                                    .sendFlag(SendFlag.DONE)
                                                    .receiveFlag(ReceiveFlag.OPENED)
                                                    .userName(testParam)
                                                    .requestRegDt(new Timestamp((new Date()).getTime()))
                                                    .build();

        given(mockRepository.save(any())).willReturn(testPrevVo)
                                         .willReturn(testPostVo);  // 같은 메소드 두번 다른 값을 리턴하는 경우 설정 방법.
        given(settleBankClient.kakaoAuth(new SettleBankKakaoAuthRequest())).willReturn(new SettleBankKakaoAuthResponse());


        //when
        SettleRequestData settleRequestData = service.sampleAuth(testParam);

        //then
        assertThat(settleRequestData).isEqualTo(testPostVo);
    }
}