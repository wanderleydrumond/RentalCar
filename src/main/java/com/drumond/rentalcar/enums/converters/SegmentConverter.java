package com.drumond.rentalcar.enums.converters;

import com.drumond.rentalcar.enums.Segment;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SegmentConverter implements AttributeConverter<Segment, Short> {
    @Override
    public Short convertToDatabaseColumn(Segment segment) {
        return Segment.toDatabaseColumn(segment);
    }

    @Override
    public Segment convertToEntityAttribute(Short dataBaseValue) {
        return Segment.toEntityAttribute(dataBaseValue);
    }
}