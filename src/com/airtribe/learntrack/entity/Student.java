package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batchName;
    private boolean active;

    public Student(String id, String firstName, String lastName, String email, String batchName) {
        super(id, firstName, lastName, email);
        this.batchName = batchName;
        this.active = true;
    }

    public Student(String id, String firstName, String lastName, String batchName) {
        super(id, firstName, lastName, null);
        this.batchName = batchName;
        this.active = true;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Batch: " + batchName + " | Active: " + active;
    }
}
