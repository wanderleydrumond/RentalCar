package com.drumond.rentalcar.enums.converters;

import com.drumond.rentalcar.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts between enumerator and its numeric value.
 */
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Short> {
    /**
     * Converts from enumeration to numeric value.
     * @param role enumerator to be converted
     * @return The correspondent {@link Short} value
     */
    @Override
    public Short convertToDatabaseColumn(Role role) {
        return Role.toDatabaseColumn(role);
    }

    /**
     * Converts from numeric to enumeration value.
     * @param dataBaseValue the {@link Short} value to be converted
     * @return The correspondent {@link Role} value
     */
    @Override
    public Role convertToEntityAttribute(Short dataBaseValue) {
        return Role.toEntityAttribute(dataBaseValue);
    }
}