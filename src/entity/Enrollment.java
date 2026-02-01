package entity;

import enums.EnrollmentStatus;
import java.time.LocalDate;

public class Enrollment {
    private String id;
    private String studentId;
    private String courseId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment(String id, String studentId, String courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDate.now();
        this.status = EnrollmentStatus.ACTIVE;
    }

    // Getters and Setters
    public String getId() { return id; }

    public String getStudentId() { return studentId; }

    public String getCourseId() { return courseId; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }

    public EnrollmentStatus getStatus() { return status; }

    public void setStatus(EnrollmentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Enrollment[" + id + "] Student: " + studentId +
                " | Course: " + courseId + " | Status: " + status;
    }
}
