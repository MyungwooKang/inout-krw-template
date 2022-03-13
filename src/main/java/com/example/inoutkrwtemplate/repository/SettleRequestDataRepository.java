package com.example.inoutkrwtemplate.repository;

import com.example.inoutkrwtemplate.repository.entity.SettleRequestData;
import com.example.inoutkrwtemplate.repository.enums.SendFlag;
import com.example.inoutkrwtemplate.repository.vo.SampleSpecialColumnSelectVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;

public interface SettleRequestDataRepository extends JpaRepository<SettleRequestData, Long> {
    /**
     * TODO
     * 1. DB파트 검수 등의 사유로 기존 검증된 쿼리 재사용시에는 native 또는 jpql 사용이 좋을것 같은데.. 생각해봐야할듯.
     * 2. Vo 매핑 방식 vs Projection ?
     */

    @QueryHints({
            @QueryHint(name = org.hibernate.annotations.QueryHints.COMMENT, value = "SettleRequestDataRepository.selectSample")
    })
    @Query("SELECT new com.example.inoutkrwtemplate.repository.vo.SampleSpecialColumnSelectVo(s.seq, s.sendFlag, s.userName) FROM SettleRequestData s " +
            "WHERE s.sendFlag =:sendFlag")
    List<SampleSpecialColumnSelectVo> findProjectionsBySendFlag(@Param("sendFlag") SendFlag sendFlag);
}
