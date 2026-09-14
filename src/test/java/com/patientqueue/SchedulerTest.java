package com.patientqueue;

import com.patientqueue.model.*;
import com.patientqueue.service.Scheduler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {
    @Test void higherPriorityIsScheduledFirst() {
        Scheduler s = new Scheduler(); s.addResource(new Resource(1, "Ward"));
        s.enqueue(new Patient(1, "Low", 1)); s.enqueue(new Patient(2, "High", 5));
        assertTrue(s.allocateNext().isPresent()); assertTrue(s.isAllocated(2));
    }
    @Test void noResourceLeavesPatientWaiting() {
        Scheduler s = new Scheduler(); s.enqueue(new Patient(1, "A", 3));
        assertTrue(s.allocateNext().isEmpty()); assertEquals(1, s.waitingCount());
    }
    @Test void duplicateResourceIdReplacesIndex() {
        Scheduler s = new Scheduler(); s.addResource(new Resource(1, "Ward")); s.addResource(new Resource(1, "ICU"));
        s.enqueue(new Patient(1, "A", 2)); assertTrue(s.allocateNext().isPresent());
    }
    @Test void invalidPriorityRejected() { assertThrows(IllegalArgumentException.class, () -> new Patient(1, "A", 6)); }
    @Test void invalidPatientIdRejected() { assertThrows(IllegalArgumentException.class, () -> new Patient(0, "A", 1)); }
    @Test void blankPatientNameRejected() { assertThrows(IllegalArgumentException.class, () -> new Patient(1, " ", 1)); }
    @Test void invalidResourceRejected() { assertThrows(IllegalArgumentException.class, () -> new Resource(0, "Ward")); }
    @Test void emptySchedulerReturnsEmpty() { assertTrue(new Scheduler().allocateNext().isEmpty()); }
    @Test void allocationCountTracksAssignments() {
        Scheduler s = new Scheduler(); s.addResource(new Resource(1, "Ward")); s.enqueue(new Patient(1, "A", 2));
        s.allocateNext(); assertEquals(1, s.allocatedCount());
    }
}
