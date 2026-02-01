# Design Notes & Architectural Decisions

## 1. Why ArrayList instead of Arrays?
We chose `ArrayList` (from the Java Collections Framework) over standard arrays (`Student[]`) for the Repositories.
* **Dynamic Sizing:** Standard arrays have a fixed size (e.g., `new Student[10]`). If we want to add an 11th student, we would have to create a new, larger array and copy all elements over. `ArrayList` handles this resizing automatically.
* **Utility Methods:** `ArrayList` provides built-in methods like `.add()`, `.isEmpty()`, and `.contains()`, which simplifies the code in the Repository layer and reduces the chance of "IndexOutOfBounds" errors.

## 2. Usage of Static Members
Static members are used in `com.airtribe.learntrack.util.IdGenerator`.
* **Reason:** We need a single, shared counter for IDs (e.g., `studentIdCounter`) that persists across the entire lifespan of the application.
* **Benefit:** If these were instance variables, every time we created a `new IdGenerator()`, the counter would reset to 0. Making them `static` ensures they belong to the class, not a specific object, guaranteeing unique IDs for every new entity.

## 3. Inheritance & Polymorphism
* **Implementation:** We created a base class `Person` containing common fields (`firstName`, `lastName`, `email`). The `Student` class extends `Person`.
* **Benefit:** This adheres to the DRY (Don't Repeat Yourself) principle. If we later add a `Trainer` or `Admin` class, they can also extend `Person` without us rewriting the name/email logic.
* **Polymorphism:** We overrode the `getDetails()` method in `Student`. This allows the system to treat a `Student` as a `Person` but still execute the specific behavior (printing the "Batch") defined in the `Student` class.

## 4. Separation of Concerns
The app is divided into layers:
* **Entity:** Defines the data structure.
* **Repository:** Handles storage (ArrayLists).
* **Service:** Handles business logic (e.g., checking for duplicate enrollments).
* **Main (UI):** Handles user input.
  This modularity makes the code readable and easy to test or upgrade (e.g., switching from ArrayList to a Database later would only require changing the Repository layer).