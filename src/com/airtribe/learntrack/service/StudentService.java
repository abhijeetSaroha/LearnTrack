package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentService {
    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(String firstName, String lastName, String email, String batchName) {
        String id = IdGenerator.generateStudentId();
        Student newStudent = new Student(id, firstName, lastName, email, batchName);

        studentRepository.add(newStudent);
        LOGGER.log(Level.INFO, "Student created successfully with ID: {0}", id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) throws EntityNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found."));
    }

    public void deactivateStudent(String id) throws EntityNotFoundException {
        Student s = getStudentById(id);
        s.setActive(false);
        LOGGER.log(Level.INFO, "Success: Student {0} has been deactivated.", id);
    }
}
