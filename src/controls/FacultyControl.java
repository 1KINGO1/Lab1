package controls;

import models.Department;
import models.Faculty;
import persons.Student;
import persons.Teacher;

public class FacultyControl extends Control {
    Faculty faculty = null;

    public FacultyControl(Faculty faculty){
        this.faculty = faculty;
    }

    private void showInstructions() {
        System.out.println("===== UKMA Control Panel (Faculty: " + ANSI_YELLOW + faculty.getName() + ANSI_RESET + ") =====");
        System.out.println("[1] | Change name");
        System.out.println("[2] | Create department");
        System.out.println("[3] | Show departments");
        System.out.println("[4] | Select department");

        System.out.println("[5] | Show students ordered by " + ANSI_GREEN + "alphabet" + ANSI_RESET);
        System.out.println("[6] | Show teachers ordered by " + ANSI_GREEN + "alphabet" + ANSI_RESET);

        System.out.println("[X] | Exit");
    }

    public void handleCommands() {
        while (true) {
            showInstructions();
            char command = requestCommand();

            System.out.println(" ");

            switch (command) {
                case '1':
                    changeName();
                    break;
                case '2':
                    createDepartment();
                    break;
                case '3':
                    showDepartments();
                    break;
                case '4':
                    selectDepartment();
                    break;
                case '5':
                    showStudentsOrderedByAlphabet();
                    break;
                case '6':
                    showTeachersOrderedByAlphabet();
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

    private void changeName(){
        while (true) {
            try{
                String newName = requestString("new faculty name");
                faculty.changeName(newName);
                System.out.println("Changed successfully!");
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void createDepartment(){
        while (true) {
            try{
                String name = requestString("department name");
                addDepartment(name);
                System.out.println("Department created successfully!");
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void addDepartment(String name) throws IllegalArgumentException{
        faculty.createDepartment(name);
    }

    private void showDepartments(){
        Department[] departments = faculty.getDepartments();
        if (departments.length == 0) {
            System.out.println("No departments found");
            return;
        }
        for (int i = 0; i < departments.length; i++) {
            System.out.println(departments[i]);
        }
    }

    private void selectDepartment(){

        if (faculty.getDepartments().length == 0) {
            System.out.println("No departments found. Please create a department first.");
            return;
        }

        while (true) {
            try{
                String name = requestString("department name");
                Department department = faculty.findDepartmentByName(name);
                if (department == null) {
                    System.out.println("Department not found");
                    continue;
                }
                DepartmentControl control = new DepartmentControl(faculty, department);
                control.handleCommands();
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void showStudentsOrderedByAlphabet(){

        if (faculty.getDepartments().length == 0) {
            System.out.println("No departments found. Please create a department first.");
            return;
        }

        Student[] students = new Student[0];

        for (Department department : faculty.getDepartments()) {
            for (Student student : department.getStudents()) {
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

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void showTeachersOrderedByAlphabet(){
        if (faculty.getDepartments().length == 0) {
            System.out.println("No departments found. Please create a department first.");
            return;
        }

        Teacher[] teachers = new Teacher[0];

        for (Department department : faculty.getDepartments()) {
            for (Teacher teacher : department.getTeachers()) {
                Teacher[] newTeachers = new Teacher[teachers.length + 1];
                for (int i = 0; i < teachers.length; i++) {
                    newTeachers[i] = teachers[i];
                }
                newTeachers[teachers.length] = teacher;
                teachers = newTeachers;
            }
        }

        for (int i = 0; i < teachers.length; i++) {
            for (int j = i + 1; j < teachers.length; j++) {
                if (teachers[i].getName().toLowerCase().compareTo(teachers[j].getName().toLowerCase()) > 0) {
                    Teacher temp = teachers[i];
                    teachers[i] = teachers[j];
                    teachers[j] = temp;
                }
            }
        }

        System.out.println("Students ordered by alphabet");

        if (teachers.length == 0) {
            System.out.println("No teachers found");
            return;
        }

        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
}
