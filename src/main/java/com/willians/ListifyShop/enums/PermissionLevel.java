package com.willians.ListifyShop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PermissionLevel {
    READ, WRITE;

    @JsonValue
    public String getValue() {
        return this.name();  // Retorna o nome do enum como string
    }

    @JsonCreator
    public static PermissionLevel forValue(String value) {
        for (PermissionLevel level : PermissionLevel.values()) {
            if (level.name().equalsIgnoreCase(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
