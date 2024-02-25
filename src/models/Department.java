package models;

import persons.Student;
import persons.Teacher;

public class Department {

    private static final int MIN_NAME_LENGTH = 3;
    private static String[] usedNames = new String[0];

    private String name = "Unknown";
    private Teacher[] teachers = new Teacher[0];
    private Student[] students = new Student[0];

    public Department(String name) {

        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("ERROR: Name is too short (min 3 symbols)");
        }

        if (isNameUsed(name)) {
            throw new IllegalArgumentException("ERROR: Name is already used");
        }

        this.name = name;
        addUsedName(name);
    }

    public String getName(){
        return this.name;
    }

    private boolean isNameUsed(String name){
        for (String usedName : usedNames) {
            if (usedName.toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private void addUsedName(String name) {
        String[] newUsedNames = new String[usedNames.length + 1];
        for (int i = 0; i < usedNames.length; i++) {
            newUsedNames[i] = usedNames[i];
        }
        newUsedNames[usedNames.length] = name;
        usedNames = newUsedNames;
    }

    public void changeName(String name) {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("ERROR: Name is too short (min 3 symbols)");
        }

        if (isNameUsed(name)) {
            throw new IllegalArgumentException("ERROR: Name is already used");
        }

        String oldName = this.name;
        this.name = name;

        for (int i = 0; i < usedNames.length; i++) {
            if (usedNames[i].equals(oldName)) {
                usedNames[i] = name;
                break;
            }
        }
    }

    public void changeTeacher(Teacher oldTeacher, Teacher newTeacher) {
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] == oldTeacher) {
                teachers[i] = newTeacher;
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Teacher not found");
    }

    public void changeStudent(Student oldStudent, Student newStudent) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == oldStudent) {
                students[i] = newStudent;
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Student not found");
    }

    public void addStudent(Student student) {
        Student[] newStudents = new Student[students.length + 1];
        for (int i = 0; i < students.length; i++) {
            newStudents[i] = students[i];
        }
        newStudents[students.length] = student;
        students = newStudents;
    }

    public void removeStudent(Student student) {
        Student[] newStudents = new Student[students.length - 1];
        int j = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != student) {
                newStudents[j] = students[i];
                j++;
            }
        }
        students = newStudents;
    }

    public Student findStudent(String name){
        for (int i = 0; i < students.length; i++) {
            if (students[i].getName().toLowerCase().equals(name.toLowerCase())) {
                return students[i];
            }
        }
        throw new IllegalArgumentException("ERROR: Student not found");
    }

    public void addTeacher(Teacher teacher) {
        Teacher[] newTeachers = new Teacher[teachers.length + 1];
        for (int i = 0; i < teachers.length; i++) {
            newTeachers[i] = teachers[i];
        }
        newTeachers[teachers.length] = teacher;
        teachers = newTeachers;
    }

    public void removeTeacher(Teacher teacher) {
        Teacher[] newTeachers = new Teacher[teachers.length - 1];
        int j = 0;
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] != teacher) {
                newTeachers[j] = teachers[i];
                j++;
            }
        }
        teachers = newTeachers;
    }

    public Teacher findTeacher(String name){
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i].getName().toLowerCase().equals(name.toLowerCase())) {
                return teachers[i];
            }
        }
        throw new IllegalArgumentException("ERROR: Teacher not found");
    }

    public Student[] getStudents() {
        return students;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    @Override
    public String toString(){
        return "Department: " +  this.name;
    }

}
