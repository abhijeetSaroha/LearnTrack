package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnrollmentService {
    private static final Logger LOGGER = Logger.getLogger(EnrollmentService.class.getName());
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void enrollStudent(String studentId, String courseId) {
        List<Enrollment> existingEnrollments = enrollmentRepository.findByStudentId(studentId);

        for (Enrollment e : existingEnrollments) {
            if (e.getCourseId().equals(courseId)) {
                LOGGER.log(Level.WARNING, "Error: Student is already enrolled in Course {0}", courseId);
                return;
            }
        }

        String id = IdGenerator.generateEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollmentRepository.add(enrollment);
        LOGGER.log(Level.INFO, "Enrollment successful! Enrollment ID: {0}", id);
    }

    public List<Enrollment> getEnrollmentsForStudent(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public void updateEnrollmentStatus(String enrollmentId, EnrollmentStatus status) {
        enrollmentRepository.findById(enrollmentId).ifPresentOrElse(
                e -> {
                    e.setStatus(status);
                    LOGGER.log(Level.INFO, "Success: Enrollment {0} marked as {1}", new Object[]{enrollmentId, status});
                },
                () -> LOGGER.log(Level.WARNING, "Error: Enrollment ID {0} not found.", enrollmentId)
        );
    }
}
