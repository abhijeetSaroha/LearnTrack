package service;

import entity.Student;
import exception.EntityNotFoundException;
import repository.StudentRepository;
import util.IdGenerator;

import java.util.List;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public void addStudent(String firstName, String lastName, String email, String batch) {
        String id = IdGenerator.generateStudentId();
        Student newStudent = new Student(id, firstName, lastName, email, batch);

        studentRepository.add(newStudent);
        System.out.println("Student created successfully with ID: " + id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) throws EntityNotFoundException {
        Student s = studentRepository.findById(id);
        if (s == null) {
            throw new EntityNotFoundException("Student with ID " + id + " not found.");
        }
        return s;
    }

    public void deactivateStudent(String id) throws EntityNotFoundException {
        Student s = getStudentById(id);
        s.setActive(false);
        System.out.println("Success: Student " + id + " has been deactivated.");
    }
}
