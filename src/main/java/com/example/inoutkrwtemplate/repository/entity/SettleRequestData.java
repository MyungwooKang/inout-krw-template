package com.example.inoutkrwtemplate.repository.entity;

import com.example.inoutkrwtemplate.repository.enums.ReceiveFlag;
import com.example.inoutkrwtemplate.repository.enums.SendFlag;
import com.example.inoutkrwtemplate.repository.enums.converter.ReceiveFlagConverter;
import com.example.inoutkrwtemplate.repository.enums.converter.SendFlagConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SETTLE_REQUEST_DATA", schema = "BTCKOREADB_OWN",
indexes = {
        //TODO Index 추가
        @Index(name = "IDX_SETTLE_REQUEST_DATA_03", columnList = "SEND_FLAG, RECV_FLAG, RET_REG_DT")
})
@DynamicInsert
@DynamicUpdate
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettleRequestData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SETTLE_REQUEST_DATA")
    @SequenceGenerator(name= "SEQ_SETTLE_REQUEST_DATA", sequenceName = "SEQ_SETTLE_REQUEST_DATA_01", allocationSize = 1)
    private Long seq;

    @Comment("테스트칼럼")
    @Column(name = "USER_NM")
    private String userName;

    /**
     * 이하 index 테스를 위한 칼럼
     */
    @Comment("에러코드")
    @Column(name = "SEND_FLAG", columnDefinition = "varchar2(1) default 'N'")
    @Convert(converter = SendFlagConverter.class)
    private SendFlag sendFlag;

    @Comment("응답수신여부")
    @Column(name = "RECV_FLAG", columnDefinition = "varchar2(1) default 'N'")
    @Convert(converter = ReceiveFlagConverter.class)
    private ReceiveFlag receiveFlag;

    @Comment("요청등록일자")
    @Column(name = "RET_REG_DT")
    @CreationTimestamp
    private Timestamp requestRegDt;
}
