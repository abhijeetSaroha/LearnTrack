package service;

import entity.Course;
import exception.EntityNotFoundException;
import repository.CourseRepository;
import util.IdGenerator;

import java.util.List;

public class CourseService {
    private CourseRepository courseRepository = new CourseRepository();

    public void addCourse(String courseName, String description, int duration) {
        String id = IdGenerator.generateCourseId();
        Course newCourse = new Course(id, courseName, description, duration);
        courseRepository.add(newCourse);
        System.out.println("Course created successfully with ID: " + id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) throws EntityNotFoundException {
        Course c = courseRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Course with ID " + id + " not found.");
        }
        return c;
    }
}
