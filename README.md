# PatientQueue — Hospital Resource Scheduling System

PatientQueue is a Java object-oriented scheduling system for assigning simulated patients to hospital resources while preventing booking conflicts and prioritizing urgent cases.

## Features
- Patient and resource domain models with encapsulation
- Priority-based scheduling using `PriorityQueue`
- Fast resource lookup using `HashMap`
- Conflict detection for overlapping allocations
- SQLite persistence through JDBC
- JUnit 5 tests for scheduling, validation, and conflict handling

## Run
```bash
mvn test
mvn package
java -jar target/patientqueue-1.0.0.jar
```

The demo seeds 200 simulated patients/resources and prints scheduling and lookup metrics.

## Structure
- `src/main/java/com/patientqueue/model` — domain classes
- `src/main/java/com/patientqueue/service` — scheduling logic
- `src/main/java/com/patientqueue/repository` — SQLite persistence
- `src/main/java/com/patientqueue` — CLI entry point
- `src/test/java` — JUnit tests
