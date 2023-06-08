package com.drumond.rentalcar.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum Segment {
    HATCH((short) 0),
    SEDAN((short) 1),
    SUV((short) 2),
    PICK_UP((short) 3);

    private final Short dataBaseValue;

    private static final Map<Short, Segment> SEGMENT_MAP = new LinkedHashMap<>();

    public static Segment toEntityAttribute(Short dataBaseValue) {
        return dataBaseValue == null ? null : SEGMENT_MAP.get(dataBaseValue);
    }

    public static Short toDatabaseColumn(Segment segment) {
        return segment == null ? null : segment.dataBaseValue;
    }
}