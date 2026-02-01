package entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student(String id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public Student(String id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, "N/A");
        this.batch = batch;
        this.active = true;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Batch: " + batch + " | Active: " + active;
    }
}
