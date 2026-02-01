package com.airtribe.learntrack.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong studentIdCounter = new AtomicLong(1000);
    private static final AtomicLong courseIdCounter = new AtomicLong(500);
    private static final AtomicLong enrollmentIdCounter = new AtomicLong(1);

    public static String generateStudentId() {
        return formatId("STU", studentIdCounter.incrementAndGet());
    }

    public static String generateCourseId() {
        return formatId("CRS", courseIdCounter.incrementAndGet());
    }

    public static String generateEnrollmentId() {
        return formatId("ENR", enrollmentIdCounter.incrementAndGet());
    }

    private static String formatId(String prefix, long count) {
        return prefix + count;
    }
}