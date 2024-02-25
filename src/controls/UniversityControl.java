package controls;

import models.Department;
import models.Faculty;
import persons.Student;
import persons.Teacher;

import java.io.IOException;
import java.sql.Array;

public class UniversityControl extends Control {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Faculty[] faculties = new Faculty[]{
            new Faculty("Information Technologies"),
            new Faculty("Physics"),
            new Faculty("Mathematics"),
            new Faculty("Biology"),
            new Faculty("Chemistry"),
            new Faculty("Economics"),
            new Faculty("Law"),
    };

    private void showInstructions() {
        System.out.println("===== UKMA Control Panel (Faculties) =====");
        System.out.println("[1] | Show all");
        System.out.println("[2] | Select by name");
        System.out.println("[3] | Delete by name");
        System.out.println("[4] | Create");

        System.out.println("[5] | Find student by name");
        System.out.println("[6] | Find student by age");
        System.out.println("[7] | Find student by course");
        System.out.println("[8] | Find teacher by name");
        System.out.println("[9] | Find teacher by age");

        System.out.println("[A] | Show students ordered by " + ANSI_BLUE + "Course" + ANSI_RESET);

        System.out.println("[X] | Exit");
    }

    public void handleCommands() {
        while (true) {
            showInstructions();
            char command = requestCommand();

            System.out.println(" ");

            switch (command) {
                case '1':
                    showFaculties();
                    break;
                case '2':
                    selectFacultyByName();
                    break;
                case '3':
                    deleteFacultyByName();
                    break;
                case '4':
                    createFaculty();
                    break;
                case '5':
                    findStudentByName();
                    break;
                case '6':
                    findStudentByAge();
                    break;
                case '7':
                    findStudentByCourse();
                    break;
                case '8':
                    findTeacherByName();
                    break;
                case '9':
                    findTeacherByAge();
                case 'A':
                case 'a':
                    showStudentsByCourse();
                    break;
                case 'x':
                case 'X':
                    return;
                default:
                    System.out.println("Unknown command: " + command);
            }

            System.out.println(" ");
        }
    }

    private void deleteFacultyByName() {
        System.out.println("== Delete faculty by name ==");
        String name = requestString("faculty name");

        Faculty faculty = findByName(name);

        if (faculty == null) {
            System.out.println("Not found!");
        } else {
            System.out.println("Successfully deleted: " + faculty.getName());
            deleteFaculty(faculty);
        }
    }

    private void showFaculties() {
        System.out.println("== Faculties ==");
        if (faculties.length == 0) {
            System.out.println("No faculties found!");
        }
        for (Faculty faculty : faculties) {
            System.out.println(faculty);
        }
    }

    private void selectFacultyByName() {

        if (faculties.length == 0) {
            System.out.println("No faculties found! Please create one first.");
            return;
        }

        System.out.println("== Find faculty by name ==");
        String name = requestString("faculty name");

        Faculty faculty = findByName(name);

        if (faculty == null) {
            System.out.println("Not found!");
        } else {
            System.out.println(faculty);
            FacultyControl control = new FacultyControl(faculty);
            control.handleCommands();
        }

    }

    private Faculty findByName(String name) {
        for (Faculty faculty : faculties) {
            if (faculty.getName().toLowerCase().contains(name.toLowerCase())) {
                return faculty;
            }
        }

        return null;
    }

    private void deleteFaculty(Faculty faculty) {
        Faculty[] newArr = new Faculty[faculties.length - 1];
        int j = 0;
        for (int i = 0; i < faculties.length; i++) {
            if (faculties[i] != faculty) {
                newArr[j] = faculties[i];
                j++;
            }
        }
        faculties = newArr;
    }

    private void createFaculty() {
        System.out.println("== Create new faculty ==");

        while (true) {
            String name = requestString("faculty name");

            Faculty existingFaculty = findByName(name);
            if (existingFaculty != null) {
                System.out.println("Faculty " + existingFaculty.getName() + " already existing!");
                continue;
            }

            try {
                Faculty faculty = new Faculty(name);
                Faculty[] newArr = new Faculty[faculties.length + 1];
                for (int i = 0; i < faculties.length; i++) {
                    newArr[i] = faculties[i];
                }
                newArr[faculties.length] = faculty;
                faculties = newArr;
                break;
            } catch (IllegalArgumentException e) {
                System.out.flush();
                System.out.println(e.getMessage());
            }

        }
    }

    private void findStudentByName() {

        String name = requestString("student name");

        boolean isFound = false;

        for (Faculty faculty : faculties) {
            for (Department department : faculty.getDepartments()) {
                for (Student student : department.getStudents()) {
                    if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                        isFound = true;
                        System.out.println(ANSI_BLUE + faculty.getName() + ANSI_RESET + " -> " + ANSI_PURPLE + department.getName() + ANSI_RESET + " Found: " + student);
                    }
                }
            }
        }

        if (!isFound) {
            System.out.println("Students not found!");
        }

    }

    private void findStudentByCourse() {

        int course = requestCourse();

        boolean isFound = false;

        for (Faculty faculty : faculties) {
            for (Department department : faculty.getDepartments()) {
                for (Student student : department.getStudents()) {
                    if (student.getCourse() == course) {
                        isFound = true;
                        System.out.println(ANSI_BLUE + faculty.getName() + ANSI_RESET + " -> " + ANSI_PURPLE + department.getName() + ANSI_RESET + " Found: " + student);
                    }
                }
            }
        }

        if (!isFound) {
            System.out.println("Students not found!");
        }

    }

    private void findStudentByAge() {

        int age = requestInt("student age");

        boolean isFound = false;

        for (Faculty faculty : faculties) {
            for (Department department : faculty.getDepartments()) {
                for (Student student : department.getStudents()) {
                    if (student.getAge() == age) {
                        isFound = true;
                        System.out.println(ANSI_BLUE + faculty.getName() + ANSI_RESET + " -> " + ANSI_PURPLE + department.getName() + ANSI_RESET + " Found: " + student);
                    }
                }
            }
        }

        if (!isFound) {
            System.out.println("Students not found!");
        }

    }

    private void findTeacherByName() {

        String name = requestString("teacher name");

        boolean isFound = false;

        for (Faculty faculty : faculties) {
            for (Department department : faculty.getDepartments()) {
                for (Teacher teacher : department.getTeachers()) {
                    if (teacher.getName().toLowerCase().contains(name.toLowerCase())) {
                        isFound = true;
                        System.out.println(ANSI_BLUE + faculty.getName() + ANSI_RESET + " -> " + ANSI_PURPLE + department.getName() + ANSI_RESET + " Found: " + teacher);
                    }
                }
            }
        }

        if (!isFound) {
            System.out.println("Teachers not found!");
        }
    }

    private void findTeacherByAge() {

        int age = requestInt("teacher age");

        boolean isFound = false;

        for (Faculty faculty : faculties) {
            for (Department department : faculty.getDepartments()) {
                for (Teacher teacher : department.getTeachers()) {
                    if (teacher.getAge() == age) {
                        isFound = true;
                        System.out.println(ANSI_BLUE + faculty.getName() + ANSI_RESET + " -> " + ANSI_PURPLE + department.getName() + ANSI_RESET + " Found: " + teacher);
                    }
                }
            }
        }

        if (!isFound) {
            System.out.println("Teachers not found!");
        }
    }

    public void showStudentsByCourse() {
        for (int i = 1; i <= 6; i++) {
            System.out.println("== Students in course " + ANSI_PURPLE + i + ANSI_RESET + " ==");
            boolean isFound = false;
            for (Faculty faculty : faculties) {
                for (Department department : faculty.getDepartments()) {
                    for (Student student : department.getStudents()) {
                        if (student.getCourse() == i) {
                            isFound = true;
                            System.out.println(ANSI_BLUE + faculty.getName() + ANSI_RESET + " -> " + ANSI_PURPLE + department.getName() + ANSI_RESET + " Found: " + student);
                        }
                    }
                }
            }
            if (!isFound) {
                System.out.println("Students not found!");
            }
        }
    }

}
