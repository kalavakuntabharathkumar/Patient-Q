package com.patientqueue.repository;

import java.sql.*;

public final class Database {
    private Database() {}
    public static Connection connect(String url) throws SQLException { return DriverManager.getConnection(url); }
    public static void initialize(Connection c) throws SQLException {
        try (Statement s = c.createStatement()) {
            s.executeUpdate("CREATE TABLE IF NOT EXISTS patients(id INTEGER PRIMARY KEY, name TEXT NOT NULL, priority INTEGER NOT NULL)");
            s.executeUpdate("CREATE TABLE IF NOT EXISTS resources(id INTEGER PRIMARY KEY, type TEXT NOT NULL)");
            s.executeUpdate("CREATE TABLE IF NOT EXISTS allocations(patient_id INTEGER, resource_id INTEGER, PRIMARY KEY(patient_id), FOREIGN KEY(patient_id) REFERENCES patients(id), FOREIGN KEY(resource_id) REFERENCES resources(id))");
        }
    }
}
