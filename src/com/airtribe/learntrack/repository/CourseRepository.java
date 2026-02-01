package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CourseRepository {
    private final Map<String, Course> courseMap;

    public CourseRepository() {
        this.courseMap = new HashMap<>();
    }

    public void add(Course course) {
        courseMap.put(course.getId(), course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courseMap.values());
    }

    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courseMap.get(id));
    }
}
