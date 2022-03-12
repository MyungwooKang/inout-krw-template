package com.example.inoutkrwtemplate.repository.enums.converter;

import com.example.inoutkrwtemplate.repository.enums.ReceiveFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ReceiveFlagConverter implements AttributeConverter<ReceiveFlag, String> {

    @Override
    public String convertToDatabaseColumn(ReceiveFlag attribute) {
        return attribute.getLegacyCode();
    }

    @Override
    public ReceiveFlag convertToEntityAttribute(String dbData) {
        return ReceiveFlag.ofLegacyCode(dbData);
    }
}
