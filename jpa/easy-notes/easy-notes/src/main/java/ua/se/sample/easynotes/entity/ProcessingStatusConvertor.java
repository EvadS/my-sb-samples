package ua.se.sample.easynotes.entity;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.se.sample.easynotes.dto.enums.ProcessingStatus;

@Converter(autoApply = true)
public class ProcessingStatusConvertor implements AttributeConverter<ProcessingStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProcessingStatus status) {
        if (status == null) {
            return null;
        }
        return status.getStatus();
    }

    @Override
    public ProcessingStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return ProcessingStatus.of(code);
    }
}
