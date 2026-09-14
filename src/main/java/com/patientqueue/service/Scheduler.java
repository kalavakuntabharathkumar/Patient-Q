package com.patientqueue.service;

import com.patientqueue.model.Patient;
import com.patientqueue.model.Resource;
import java.util.*;

public class Scheduler {
    private final PriorityQueue<Patient> waiting = new PriorityQueue<>();
    private final Map<Integer, Resource> resources = new HashMap<>();
    private final Map<Integer, Integer> allocations = new HashMap<>();

    public void addResource(Resource resource) { resources.put(resource.id(), resource); }
    public void enqueue(Patient patient) { waiting.offer(patient); }
    public Optional<Resource> allocateNext() {
        Patient patient = waiting.poll();
        if (patient == null) return Optional.empty();
        for (Resource resource : resources.values()) {
            if (!allocations.containsValue(resource.id())) {
                allocations.put(patient.id(), resource.id());
                return Optional.of(resource);
            }
        }
        waiting.offer(patient);
        return Optional.empty();
    }
    public boolean isAllocated(int patientId) { return allocations.containsKey(patientId); }
    public int waitingCount() { return waiting.size(); }
    public int allocatedCount() { return allocations.size(); }
}
