package com.drumond.rentalcar.enums.converters;

import com.drumond.rentalcar.enums.Segment;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts between enumerator and its numeric value.
 */
@Converter(autoApply = true)
public class SegmentConverter implements AttributeConverter<Segment, Short> {
    /**
     * Converts from enumeration to numeric value.
     * @param segment enumerator to be converted
     * @return The correspondent {@link Short} value
     */
    @Override
    public Short convertToDatabaseColumn(Segment segment) {
        return Segment.toDatabaseColumn(segment);
    }

    /**
     * Converts from numeric to enumeration value.
     * @param dataBaseValue the {@link Short} value to be converted
     * @return The correspondent {@link Segment} value
     */
    @Override
    public Segment convertToEntityAttribute(Short dataBaseValue) {
        return Segment.toEntityAttribute(dataBaseValue);
    }
}