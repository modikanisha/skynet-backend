package com.skynet.commons.enums;

import java.util.Arrays;

public enum Status {
    ACTIVE("Active"),
    DEACTIVATE("Deactivate");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Status getStatus(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findAny().orElse(null);
    }
}
