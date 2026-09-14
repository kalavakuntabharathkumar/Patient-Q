package com.patientqueue.model;

public record Patient(int id, String name, int priority) implements Comparable<Patient> {
    public Patient {
        if (id <= 0 || name == null || name.isBlank()) throw new IllegalArgumentException("Invalid patient");
        if (priority < 1 || priority > 5) throw new IllegalArgumentException("Priority must be 1-5");
    }
    @Override public int compareTo(Patient other) { return Integer.compare(other.priority, priority); }
}
