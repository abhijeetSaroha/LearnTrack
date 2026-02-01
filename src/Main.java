import entity.Course;
import entity.Enrollment;
import entity.Student;
import exception.EntityNotFoundException;
import service.CourseService;
import service.EnrollmentService;
import service.StudentService;

private static StudentService studentService = new StudentService();
private static CourseService courseService = new CourseService();
private static EnrollmentService enrollmentService = new EnrollmentService();

private static Scanner scanner = new Scanner(System.in);

void main() {
    IO.println("=== Welcome to LearnTrack ===");

    boolean running = true;
    while (running) {
        IO.println("\n--- MAIN MENU ---");
        IO.println("1. Student Management");
        IO.println("2. Course Management");
        IO.println("3. Enrollment Management");
        IO.println("4. Exit");
        IO.print("Enter choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
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
                    IO.println("Exiting LearnTrack. Goodbye!");
                    break;
                default:
                    IO.println("Invalid option. Please try again.");
            }
        } catch (NumberFormatException e) {
            IO.println("Error: Please enter a valid number.");
        } catch (Exception e) {
            IO.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}

// --- Student Menu Handling ---
private static void handleStudentMenu() {
    IO.println("\n-- Student Management --");
    IO.println("1. Add Student");
    IO.println("2. List All Students");
    IO.println("3. Find Student by ID");
    IO.println("4. Deactivate Student");
    IO.print("Enter choice: ");

    try {
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                IO.print("First Name: ");
                String first = scanner.nextLine();
                IO.print("Last Name: ");
                String last = scanner.nextLine();
                IO.print("Email: ");
                String email = scanner.nextLine();
                IO.print("Batch: ");
                String batch = scanner.nextLine();
                studentService.addStudent(first, last, email, batch);
                break;
            case 2:
                List<Student> students = studentService.getAllStudents();
                if (students.isEmpty()) IO.println("No students found.");
                for (Student s : students) IO.println(s.getDetails());
                break;
            case 3:
                IO.print("Enter Student ID: ");
                String id = scanner.nextLine();
                Student s = studentService.getStudentById(id);
                IO.println("Found: " + s.getDetails());
                break;
            case 4:
                IO.print("Enter Student ID to deactivate: ");
                String deactId = scanner.nextLine();
                studentService.deactivateStudent(deactId);
                break;
            default:
                IO.println("Invalid option.");
        }
    } catch (EntityNotFoundException e) {
        IO.println("Error: " + e.getMessage());
    } catch (Exception e) {
        IO.println("Invalid input.");
    }
}

// --- Course Menu Handling ---
private static void handleCourseMenu() {
    IO.println("\n-- Course Management --");
    IO.println("1. Add Course");
    IO.println("2. List All Courses");
    IO.print("Enter choice: ");

    try {
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                IO.print("Course Name: ");
                String name = scanner.nextLine();
                IO.print("Description: ");
                String desc = scanner.nextLine();
                IO.print("Duration (weeks): ");
                int duration = Integer.parseInt(scanner.nextLine());
                courseService.addCourse(name, desc, duration);
                break;
            case 2:
                List<Course> courses = courseService.getAllCourses();
                if (courses.isEmpty()) IO.println("No courses found.");
                for (Course c : courses) IO.println(c);
                break;
            default:
                IO.println("Invalid option.");
        }
    } catch (Exception e) {
        IO.println("Error processing course input.");
    }
}

// --- Enrollment Menu Handling ---
private static void handleEnrollmentMenu() {
    IO.println("\n-- Enrollment Management --");
    IO.println("1. Enroll Student in Course");
    IO.println("2. View Student Enrollments");
    IO.println("3. Mark Enrollment as Completed");
    IO.print("Enter choice: ");

    try {
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                IO.print("Enter Student ID: ");
                String sId = scanner.nextLine();
                studentService.getStudentById(sId);

                IO.print("Enter Course ID: ");
                String cId = scanner.nextLine();
                courseService.getCourseById(cId);

                enrollmentService.enrollStudent(sId, cId);
                break;
            case 2:
                IO.print("Enter Student ID: ");
                String searchId = scanner.nextLine();
                List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(searchId);
                if (enrollments.isEmpty()) IO.println("No enrollments found for this student.");
                for (Enrollment e : enrollments) IO.println(e);
                break;
            case 3:
                IO.print("Enter Enrollment ID (e.g., ENR1): ");
                String enrId = scanner.nextLine();
                enrollmentService.updateEnrollmentStatus(enrId, enums.EnrollmentStatus.COMPLETED);
                break;
            default:
                IO.println("Invalid option.");
        }
    } catch (EntityNotFoundException e) {
        IO.println("Operation Failed: " + e.getMessage());
    } catch (Exception e) {
        IO.println("Invalid input.");
    }
}