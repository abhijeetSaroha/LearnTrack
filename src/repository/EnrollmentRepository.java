package repository;

import entity.Enrollment;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    private List<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    // Find all enrollments for a specific student
    public List<Enrollment> findByStudentId(String studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId().equals(studentId)) {
                result.add(e);
            }
        }
        return result;
    }
}
