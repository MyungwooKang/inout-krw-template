package com.example.inoutkrwtemplate.repository.enums.converter;

import com.example.inoutkrwtemplate.repository.enums.SendFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SendFlagConverter implements AttributeConverter<SendFlag, String> {

    @Override
    public String convertToDatabaseColumn(SendFlag attribute) {
        return attribute.getLegacyCode();
    }

    @Override
    public SendFlag convertToEntityAttribute(String dbData) {
        return SendFlag.ofLegacyCode(dbData);
    }
}
