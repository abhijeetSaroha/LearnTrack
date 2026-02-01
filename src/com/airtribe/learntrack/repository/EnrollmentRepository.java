package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EnrollmentRepository {
    private final Map<String, Enrollment> enrollmentMap;

    public EnrollmentRepository() {
        this.enrollmentMap = new HashMap<>();
    }

    public void add(Enrollment enrollment) {
        enrollmentMap.put(enrollment.getId(), enrollment);
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollmentMap.values());
    }

    public Optional<Enrollment> findById(String id) {
        return Optional.ofNullable(enrollmentMap.get(id));
    }

    public List<Enrollment> findByStudentId(String studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollmentMap.values()) {
            if (e.getStudentId().equals(studentId)) {
                result.add(e);
            }
        }
        return result;
    }
}
