package controls;

import models.Department;
import models.Faculty;
import persons.*;

public class DepartmentControl extends Control {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    Faculty faculty = null;
    Department department = null;

    public DepartmentControl(Faculty faculty, Department department) {

        this.faculty = faculty;
        this.department = department;

    }

    private void showInstructions() {
        System.out.println("===== UKMA Control Panel (Faculty: " + ANSI_YELLOW + faculty.getName() + " -> " + department.getName() + ANSI_RESET + ") =====");
        System.out.println("[1] | Change name");
        System.out.println("[2] | Add student");
        System.out.println("[3] | Change student");
        System.out.println("[4] | Remove student");
        System.out.println("[5] | Show students");
        System.out.println("[6] | Add teacher");
        System.out.println("[7] | Change teacher");
        System.out.println("[8] | Remove teacher");
        System.out.println("[9] | Show teachers");

        System.out.println("[A] | Show students by " + ANSI_BLUE + "course" + ANSI_RESET);
        System.out.println("[B] | Show students ordered by " + ANSI_GREEN + "alphabet" + ANSI_RESET);
        System.out.println("[C] | Show teachers ordered by " + ANSI_GREEN + "alphabet" + ANSI_RESET);
        System.out.println("[D] | Show teachers ordered by " + ANSI_RED + "defined course" + ANSI_RESET);
        System.out.println("[F] | Show teachers ordered by " + ANSI_RED + "defined course" + ANSI_RESET + " and " + ANSI_GREEN + "alphabet" + ANSI_RESET);

        System.out.println("[X] | Exit");
    }

    public void handleCommands() {
        while (true) {
            showInstructions();
            char command = requestCommand();

            System.out.println(" ");

            try {
                switch (command) {
                    case '1':
                        changeName();
                        break;
                    case '2':
                        addStudent();
                        break;
                    case '3':
                        checkStudentsNotEmpty();
                        changeStudent();
                        break;
                    case '4':
                        checkStudentsNotEmpty();
                        removeStudent();
                        break;
                    case '5':
                        showStudents();
                        break;
                    case '6':
                        addTeacher();
                        break;
                    case '7':
                        checkTeachersNotEmpty();
                        changeTeacher();
                        break;
                    case '8':
                        checkTeachersNotEmpty();
                        removeTeacher();
                        break;
                    case '9':
                        showTeachers();
                        break;
                    case 'A':
                    case 'a':
                        showStudentsOrderedByCourse();
                        break;
                    case 'B':
                    case 'b':
                        showStudentOrderByAlphabet();
                        break;
                    case 'C':
                    case 'c':
                        showTeachersOrderByAlphabet();
                        break;
                    case 'D':
                    case 'd':
                        showStudentsByCourse();
                        break;
                    case 'F':
                    case 'f':
                        showStudentsByCourseOrderByAlphabet();
                        break;
                    case 'x':
                    case 'X':
                        return;
                    default:
                        System.out.println("Unknown command: " + command);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }


            System.out.println(" ");
        }
    }
    private void checkStudentsNotEmpty() {
        if (department.getStudents().length == 0) {
            throw new IllegalArgumentException("No students found. Add some students first.");
        }
    }

    private void checkTeachersNotEmpty() {
        if (department.getTeachers().length == 0) {
            throw new IllegalArgumentException("No teachers found. Add some teachers first.");
        }
    }

    private void changeName() {
        while (true) {
            try {
                String newName = requestString("new department name");
                department.changeName(newName);
                System.out.println("Changed successfully!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addStudent() {
        while (true) {
            try {
                String name = requestString("student name");
                int age = requestInt("student age");
                int course = requestInt("student course");

                Student student = new Student(name, age, course);
                department.addStudent(student);

                System.out.println("Added successfully!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showStudents() {
        Student[] students = department.getStudents();

        if (students.length == 0) {
            System.out.println("No students found");
            return;
        }

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
    }

    private void removeStudent() {
        while (true) {
            try {
                String name = requestString("student name");

                Student student = department.findStudent(name);
                department.removeStudent(student);

                System.out.println("Removed successfully!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addTeacher() {
        while (true) {
            try {
                String name = requestString("teacher name");
                int age = requestInt("teacher age");

                Teacher teacher = new Teacher(name, age);
                department.addTeacher(teacher);

                System.out.println("Added successfully!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showTeachers() {
        Teacher[] teachers = department.getTeachers();

        if (teachers.length == 0) {
            System.out.println("No teachers found");
            return;
        }

        for (int i = 0; i < teachers.length; i++) {
            System.out.println(teachers[i]);
        }
    }

    private void removeTeacher() {
        while (true) {
            try {
                String name = requestString("teacher name");

                Teacher teacher = department.findTeacher(name);
                department.removeTeacher(teacher);

                System.out.println("Removed successfully!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void changeStudent() {
        while (true) {
            try {
                String name = requestString("student name");
                Student student = department.findStudent(name);
                String newName = requestString("new student name (Previous: " + student.getName() + ")", student.getName());
                int newAge = requestInt("new student age (Previous: " + student.getAge() + ")", student.getAge());
                int newCourse = requestInt("new student course (Previous: " + student.getCourse() + ")", student.getCourse());

                Student newStudent = new Student(newName, newAge, newCourse);
                department.changeStudent(student, newStudent);

                System.out.println("Changed successfully!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void changeTeacher() {
        while (true) {
            try {
                String name = requestString("teacher name");
                Teacher teacher = department.findTeacher(name);
                String newName = requestString("new teacher name (Previous: " + teacher.getName() + ")", teacher.getName());
                int newAge = requestInt("new teacher age (Previous: " + teacher.getAge() + ")", teacher.getAge());

                Teacher newTeacher = new Teacher(newName, newAge);
                department.changeTeacher(teacher, newTeacher);

                System.out.println("Changed successfully!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showStudentsOrderedByCourse() {
        for (int i = 1; i <= 6; i++) {
            System.out.println("== Students in course " + ANSI_PURPLE + i + ANSI_RESET + " ==");
            boolean isFound = false;
            for (Student student : department.getStudents()) {
                if (student.getCourse() == i) {
                    isFound = true;
                    System.out.println(ANSI_BLUE + faculty.getName() + ANSI_RESET + " -> " + ANSI_PURPLE + department.getName() + ANSI_RESET + " Found: " + student);
                }
            }
            if (!isFound) {
                System.out.println("Students not found!");
            }
        }
    }

    private void showStudentOrderByAlphabet() {
        Student[] students = department.getStudents();

        for (int i = 0; i < students.length; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if (students[i].getName().toLowerCase().compareTo(students[j].getName().toLowerCase()) > 0) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }

        System.out.println("Students ordered by alphabet");

        if (students.length == 0) {
            System.out.println("No students found");
            return;
        }

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
    }

    private void showTeachersOrderByAlphabet() {
        Teacher[] teachers = department.getTeachers();

        for (int i = 0; i < teachers.length; i++) {
            for (int j = i + 1; j < teachers.length; j++) {
                if (teachers[i].getName().toLowerCase().compareTo(teachers[j].getName().toLowerCase()) > 0) {
                    Teacher temp = teachers[i];
                    teachers[i] = teachers[j];
                    teachers[j] = temp;
                }
            }
        }

        System.out.println("Teachers ordered by alphabet");

        if (teachers.length == 0) {
            System.out.println("No teachers found");
            return;
        }

        for (int i = 0; i < teachers.length; i++) {
            System.out.println(teachers[i]);
        }
    }

    private void showStudentsByCourse() {

        int course = requestCourse();

        System.out.println("== Students in course " + ANSI_PURPLE + course + ANSI_RESET + " ==");
        boolean isFound = false;
        for (Student student : department.getStudents()) {
            if (student.getCourse() == course) {
                isFound = true;
                System.out.println(ANSI_BLUE + faculty.getName() + ANSI_RESET + " -> " + ANSI_PURPLE + department.getName() + ANSI_RESET + " Found: " + student);
            }
        }
        if (!isFound) {
            System.out.println("Students not found!");
        }
    }

    private void showStudentsByCourseOrderByAlphabet() {
        int course = requestCourse();

        Student[] students = new Student[0];

        for (Student student : department.getStudents()) {
            if (student.getCourse() == course) {
                Student[] newStudents = new Student[students.length + 1];
                for (int i = 0; i < students.length; i++) {
                    newStudents[i] = students[i];
                }
                newStudents[students.length] = student;
                students = newStudents;
            }
        }

        for (int i = 0; i < students.length; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if (students[i].getName().toLowerCase().compareTo(students[j].getName().toLowerCase()) > 0) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }

        System.out.println("Students ordered by alphabet");

        if (students.length == 0) {
            System.out.println("No students found");
            return;
        }

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
    }

}
