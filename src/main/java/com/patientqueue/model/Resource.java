package com.patientqueue.model;

public record Resource(int id, String type) {
    public Resource {
        if (id <= 0 || type == null || type.isBlank()) throw new IllegalArgumentException("Invalid resource");
    }
}
