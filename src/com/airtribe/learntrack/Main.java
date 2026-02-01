package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.enums.EnrollmentStatus;

import java.util.List;
import java.util.Scanner;

public class Main {
    // Services
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Dependency Injection Initialization
        StudentRepository studentRepo = new StudentRepository();
        CourseRepository courseRepo = new CourseRepository();
        EnrollmentRepository enrollmentRepo = new EnrollmentRepository();

        studentService = new StudentService(studentRepo);
        courseService = new CourseService(courseRepo);
        enrollmentService = new EnrollmentService(enrollmentRepo);

        System.out.println("=== Welcome to LearnTrack ===");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;

                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        handleStudentMenu();
                        break;
                    case 2:
                        handleCourseMenu();
                        break;
                    case 3:
                        handleEnrollmentMenu();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // --- Student Menu Handling ---
    private static void handleStudentMenu() {
        System.out.println("\n-- Student Management --");
        System.out.println("1. Add Student");
        System.out.println("2. List All Students");
        System.out.println("3. Find Student by ID");
        System.out.println("4. Deactivate Student");
        System.out.print("Enter choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("First Name: ");
                    String first = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String last = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Batch: ");
                    String batch = scanner.nextLine();
                    studentService.addStudent(first, last, email, batch);
                    break;
                case 2:
                    List<Student> students = studentService.getAllStudents();
                    if (students.isEmpty()) System.out.println("No students found.");
                    for (Student s : students) System.out.println(s.getDetails());
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    Student s = studentService.getStudentById(id);
                    System.out.println("Found: " + s.getDetails());
                    break;
                case 4:
                    System.out.print("Enter Student ID to deactivate: ");
                    String deactId = scanner.nextLine();
                    studentService.deactivateStudent(deactId);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    // --- Course Menu Handling ---
    private static void handleCourseMenu() {
        System.out.println("\n-- Course Management --");
        System.out.println("1. Add Course");
        System.out.println("2. List All Courses");
        System.out.print("Enter choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Course Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Duration (weeks): ");
                    int duration = Integer.parseInt(scanner.nextLine());
                    courseService.addCourse(name, desc, duration);
                    break;
                case 2:
                    List<Course> courses = courseService.getAllCourses();
                    if (courses.isEmpty()) System.out.println("No courses found.");
                    for (Course c : courses) System.out.println(c);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Error processing course input.");
        }
    }

    // --- Enrollment Menu Handling ---
    private static void handleEnrollmentMenu() {
        System.out.println("\n-- Enrollment Management --");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. View Student Enrollments");
        System.out.println("3. Mark Enrollment as Completed");
        System.out.println("4. Cancel Enrollment");
        System.out.print("Enter choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String sId = scanner.nextLine();
                    // Validation Check
                    try {
                        studentService.getStudentById(sId);
                    } catch (EntityNotFoundException e) {
                        System.out.println("Validation Error: " + e.getMessage());
                        return;
                    }

                    System.out.print("Enter Course ID: ");
                    String cId = scanner.nextLine();
                    // Validation Check
                    try {
                        courseService.getCourseById(cId);
                    } catch (EntityNotFoundException e) {
                        System.out.println("Validation Error: " + e.getMessage());
                        return;
                    }

                    enrollmentService.enrollStudent(sId, cId);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String searchId = scanner.nextLine();
                    List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(searchId);
                    if (enrollments.isEmpty()) System.out.println("No enrollments found for this student.");
                    for (Enrollment e : enrollments) System.out.println(e);
                    break;
                case 3:
                    System.out.print("Enter Enrollment ID (e.g., ENR1): ");
                    String enrId = scanner.nextLine();
                    enrollmentService.updateEnrollmentStatus(enrId, EnrollmentStatus.COMPLETED);
                    break;
                case 4:
                    System.out.print("Enter Enrollment ID to Cancel (e.g., ENR1): ");
                    String cancelId = scanner.nextLine();
                    enrollmentService.updateEnrollmentStatus(cancelId, com.airtribe.learntrack.enums.EnrollmentStatus.CANCELLED);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }
}
