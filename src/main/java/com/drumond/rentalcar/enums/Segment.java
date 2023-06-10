package com.drumond.rentalcar.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the different car segments available in the system.
 *
 * <p>Each {@link Segment} has numeric a value.</p>
 * <ul>
 *   <li>HATCH: value = 0</li>
 *   <li>SEDAN: value = 1</li>
 *   <li>SUV: value = 2</li>
 *   <li>PICK_UP: value = 3</li>
 * </ul>
 *
 * @author Wanderley Drumond
 */
@AllArgsConstructor
@Getter
public enum Segment {
    HATCH((short) 1),
    SEDAN((short) 2),
    SUV((short) 3),
    PICK_UP((short) 4);

    /**
     * Value that is storaged in database.
     */
    private final Short DATABASE_VALUE;

    /**
     * Storages every {@link Segment} value.
     */
    private static final Map<Short, Segment> SEGMENT_MAP = new LinkedHashMap<>();

    /**
     * Changes the {@link Short} value correspondent into {@link Segment} type.
     * @param dataBaseValue {@link Short} value to be converted
     * @return The {@link Segment} correspondent
     */
    public static Segment toEntityAttribute(Short dataBaseValue) {
        return dataBaseValue == null ? null : SEGMENT_MAP.get(dataBaseValue);
    }

    /**
     * Changes the {@link Segment} value correspondent into {@link Short} type.
     * @param segment {@link Segment} value to be converted
     * @return The {@link Short} correspondent
     */
    public static Short toDatabaseColumn(Segment segment) {
        return segment == null ? null : segment.DATABASE_VALUE;
    }
}