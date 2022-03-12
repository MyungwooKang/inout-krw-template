package com.example.inoutkrwtemplate.repository.vo;

import com.example.inoutkrwtemplate.repository.enums.SendFlag;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SampleSpecialColumnSelectVo {
    private Long seq;
    private SendFlag sendFlag;
    private String userName;
}
