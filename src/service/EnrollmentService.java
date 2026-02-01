package service;

import entity.Enrollment;
import repository.EnrollmentRepository;
import util.IdGenerator;
import enums.EnrollmentStatus;

import java.util.List;

public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    public void enrollStudent(String studentId, String courseId) {
        List<Enrollment> existingEnrollments = enrollmentRepository.findByStudentId(studentId);

        for (Enrollment e : existingEnrollments) {
            if (e.getCourseId().equals(courseId)) {
                System.out.println("Error: Student is already enrolled in Course " + courseId);
                return;
            }
        }

        String id = IdGenerator.generateEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollmentRepository.add(enrollment);
        System.out.println("Enrollment successful! Enrollment ID: " + id);
    }

    public List<Enrollment> getEnrollmentsForStudent(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public void updateEnrollmentStatus(String enrollmentId, EnrollmentStatus status) {
        for (Enrollment e : enrollmentRepository.findAll()) {
            if (e.getId().equals(enrollmentId)) {
                e.setStatus(status);
                System.out.println("Success: Enrollment " + enrollmentId + " marked as " + status);
                return;
            }
        }
        System.out.println("Error: Enrollment ID " + enrollmentId + " not found.");
    }
}
