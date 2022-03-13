package com.example.inoutkrwtemplate.controller;

import com.example.inoutkrwtemplate.config.TestProperties;
import com.example.inoutkrwtemplate.controller.dto.AuthRequest;
import com.example.inoutkrwtemplate.repository.entity.SettleRequestData;
import com.example.inoutkrwtemplate.repository.enums.ReceiveFlag;
import com.example.inoutkrwtemplate.repository.enums.SendFlag;
import com.example.inoutkrwtemplate.service.AuthService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.sql.Timestamp;
import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;


@ActiveProfiles("test")
@WebMvcTest(AuthController.class)
@Import(TestProperties.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    AuthService service;

    @Autowired
    private TestProperties properties;

    @Test
    @DisplayName("컨트롤러 테스트 예제")
    void sampleAuth() throws Exception {
        //given
        String testParam = "test";

        SettleRequestData testVo = SettleRequestData.builder()
                                                   .seq(1L)
                                                   .sendFlag(SendFlag.DONE)
                                                   .receiveFlag(ReceiveFlag.OPENED)
                                                   .userName(testParam)
                                                   .requestRegDt(new Timestamp((new Date()).getTime()))
                                                   .build();

        given(service.sampleAuth(testParam)).willReturn(testVo);



        //when
        AuthRequest testRequest = new AuthRequest();
        testRequest.setUsername(testParam);
        Gson gson = new Gson();
        String body = gson.toJson(testRequest);

        ResultActions resultActions = mvc.perform(post("/auth")
                                                 .contentType(MediaType.APPLICATION_JSON)
                                                 .accept(MediaType.APPLICATION_JSON)
                                                 .content(body))
                                         .andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                //TODO ... .andExpect()
                .andExpect(jsonPath("$.data.sampleField", is(testParam)));

    }

}