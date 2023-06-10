package com.drumond.rentalcar.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the different user roles available in the system.
 *
 * <p>Each role has a value.</p>
 * <ul>
 *   <li>CLIENT: value = 1</li>
 *   <li>EMPLOYEE: value = 2</li>
 *   <li>MANAGER: value = 3</li>
 * </ul>
 *
 * @author Wanderley Drumond
 */
@AllArgsConstructor
@Getter
public enum Role {
    CLIENT((short) 1),
    EMPLOYEE((short) 2),
    MANAGER((short) 3);

    /**
     * Value that is storaged in database.
     */
    private final Short DATABASE_VALUE;

    /**
     * Storages every {@link Role} value.
     */
    private static final Map<Short, Role> ROLE_MAP = new LinkedHashMap<>();

    static {
        for (Role roleElement : Role.values()) {
            ROLE_MAP.put(roleElement.DATABASE_VALUE, roleElement);
        }
    }

    /**
     * Changes the {@link Short} value correspondent into {@link Role} type.
     * @param dataBaseValue {@link Short} value to be converted
     * @return The {@link Role} correspondent
     */
    public static Role toEntityAttribute(Short dataBaseValue) {
        return dataBaseValue == null ? null : ROLE_MAP.get(dataBaseValue);
    }

    /**
     * Changes the {@link Role} value correspondent into {@link Short} type.
     * @param role {@link Role} value to be converted
     * @return The {@link Short} correspondent
     */
    public static Short toDatabaseColumn(Role role) {
        return role == null ? null : role.DATABASE_VALUE;
    }
}