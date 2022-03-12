package com.example.inoutkrwtemplate.repository;

import com.example.inoutkrwtemplate.config.TestProperties;
import com.example.inoutkrwtemplate.repository.entity.SettleRequestData;
import com.example.inoutkrwtemplate.repository.enums.ReceiveFlag;
import com.example.inoutkrwtemplate.repository.enums.SendFlag;
import com.example.inoutkrwtemplate.repository.vo.SampleSpecialColumnSelectVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)  // 트랜잭션 설정이 필요 없는 경우
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 해당 설정이 없으면 자동으로 inmemory db 테스트를 진행하며, h2 db등의 추가 설정이 필요함. 근데 oracle 종속적인 부분이 있기 때문에 off 설정함.
@Import(TestProperties.class)
class SettleRequestDataRepositoryTest {

    @Autowired
    private SettleRequestDataRepository repository;

    @Autowired
    private TestProperties properties;

    @Test
    void findProjectionsBySendFlag() {
        //given
        String testParam = "tester";

        SettleRequestData entity = SettleRequestData.builder()
                                                   .seq(1L)
                                                   .sendFlag(SendFlag.DONE)
                                                   .receiveFlag(ReceiveFlag.OPENED)
                                                   .userName(testParam)
                                                   .requestRegDt(new Timestamp((new Date()).getTime())).build();

        repository.save(entity);

        //when
        List<SampleSpecialColumnSelectVo> projectionsBySendFlag = repository.findProjectionsBySendFlag(SendFlag.DONE);

        //then
        assertThat(projectionsBySendFlag.size()).isEqualTo(1);
        assertThat(projectionsBySendFlag.get(0).getUserName()).isEqualTo(testParam);
    }
}
