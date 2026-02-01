# JVM Basics

## 1. JDK, JRE, and JVM
* **JDK (Java Development Kit):** This is the full toolkit for developers. It includes the compiler (`javac`) to turn code into bytecode, the JRE to run it, and other tools like debuggers.
* **JRE (Java Runtime Environment):** This is for users who just want to run Java programs. It includes the JVM and standard libraries but does not include the compiler.
* **JVM (Java Virtual Machine):** The engine that actually runs the application. It loads the code, verifies it, and executes it.

## 2. What is Bytecode?
When we compile `com.airtribe.learntrack.Main.java` using `javac`, it doesn't turn into machine code (0s and 1s) immediately. It turns into a `.class` file containing **Bytecode**. Bytecode is an intermediate set of instructions that the JVM understands.

## 3. "Write Once, Run Anywhere"
Because we compile to Bytecode and not specific machine code (like Windows or Mac code), we can take our `.class` files to any computer. As long as that computer has a JVM installed, it can interpret the Bytecode and run the program. The JVM acts as a translator between our universal Bytecode and the specific machine's hardware.