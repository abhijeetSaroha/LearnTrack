package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentRepository {
    private final Map<String, Student> studentMap;

    public StudentRepository() {
        this.studentMap = new HashMap<>();
    }

    public void add(Student student) {
        studentMap.put(student.getId(), student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    public Optional<Student> findById(String id) {
        return Optional.ofNullable(studentMap.get(id));
    }
}
