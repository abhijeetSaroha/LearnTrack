# LearnTrack - Student & Course Management System

## Project Overview
LearnTrack is a console-based Core Java application designed to manage Students, Courses, and Enrollments. It demonstrates fundamental Object-Oriented Programming (OOP) principles, including Encapsulation, Inheritance, and Polymorphism, along with clean architectural patterns.

## Features
* **Student Management:** Add new students, view details, and search by ID.
* **Course Management:** Create courses with descriptions and durations.
* **Enrollment System:** Enroll students in courses and track active enrollments.
* **Data Integrity:** Prevents duplicate enrollments and handles invalid IDs gracefully.

## Technical Highlights
* **Architecture:** Separation of concerns using Service, Repository, and Entity layers.
* **Data Structures:** Uses `ArrayList` for dynamic data storage (In-Memory).
* **Error Handling:** Custom `EntityNotFoundException` for robust user feedback.
* **Utilities:** Static ID generators for unique com.airtribe.learntrack.entity identification.

## How to Run
Prerequisite: Java Development Kit (JDK) installed.

1.  **Compile the code:**
    Open your terminal/command prompt in the `src` folder.
    ```bash
    javac com.airtribe.learntrack.Main.java
    ```

2.  **Run the application:**
    ```bash
    java com.airtribe.learntrack.Main
    ```

## Directory Structure
* `src/`: Source code.
* `docs/`: Design decisions and JVM documentation.