package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseService {
    private static final Logger LOGGER = Logger.getLogger(CourseService.class.getName());
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(String courseName, String description, int duration) {
        String id = IdGenerator.generateCourseId();
        Course newCourse = new Course(id, courseName, description, duration);
        courseRepository.add(newCourse);
        LOGGER.log(Level.INFO, "Course created successfully with ID: {0}", id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) throws EntityNotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with ID " + id + " not found."));
    }
}
