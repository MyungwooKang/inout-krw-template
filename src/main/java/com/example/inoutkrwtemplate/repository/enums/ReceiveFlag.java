package com.example.inoutkrwtemplate.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@AllArgsConstructor
@Getter
public enum ReceiveFlag {
    OPENED("N", "대기"),
    TIMEOUT("T", "Timeout"),
    DONE("Y", "응답수신"),
    FAILED("F", "응답수신실패"),
    UNKNOWN("0", "알수없는 코드");

    private String legacyCode;
    private String description;

    public static ReceiveFlag ofLegacyCode(String legacyCode) {
        return Arrays.stream(ReceiveFlag.values())
                .filter(v -> v.getLegacyCode().equals(legacyCode))
                .findAny()
                .orElse(UNKNOWN);
    }
}
