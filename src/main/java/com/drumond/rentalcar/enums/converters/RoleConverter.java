package com.drumond.rentalcar.enums.converters;

import com.drumond.rentalcar.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Short> {
    @Override
    public Short convertToDatabaseColumn(Role role) {
        return Role.toDatabaseColumn(role);
    }

    @Override
    public Role convertToEntityAttribute(Short dataBaseValue) {
        return Role.toEntityAttribute(dataBaseValue);
    }
}