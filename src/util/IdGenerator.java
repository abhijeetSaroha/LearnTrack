package util;

public class IdGenerator {
    private static int studentIdCounter = 1000;
    private static int courseIdCounter = 500;
    private static int enrollmentIdCounter = 1;

    public static String generateStudentId() {
        return "STU" + (++studentIdCounter);
    }

    public static String generateCourseId() {
        return "CRS" + (++courseIdCounter);
    }

    public static String generateEnrollmentId() {
        return "ENR" + (++enrollmentIdCounter);
    }
}
