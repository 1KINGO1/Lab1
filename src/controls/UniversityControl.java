package controls;

import models.Faculty;

import java.io.IOException;
import java.sql.Array;

public class UniversityControl extends Control {

    private Faculty[] faculties = new Faculty[0];

    private void showInstructions() {
        System.out.println("===== UKMA Control Panel (Faculties) =====");
        System.out.println("[1] | Show all");
        System.out.println("[2] | Find by name");
        System.out.println("[3] | Create");
        System.out.println("[4] | Delete");
        System.out.println("[X] | Exit");
    }

    public void handleCommands() {
        while (true) {
            showInstructions();
            char command = requestCommand();

            switch (command) {
                case '1':
                    showFaculties();
                    break;
                case '2':
                    selectFacultyByName();
                    break;
                case '3':
                    createFaculty();
                    break;
                case 'X':
                    return;
                default:
                    System.out.println("Unknown command: " + command);
            }
        }
    }


    private void showFaculties() {
        System.out.println(" ");
        System.out.println("== Faculties ==");
        for (Faculty faculty : faculties) {
            System.out.println(faculty);
        }
        System.out.println(" ");
    }

    private void selectFacultyByName() {
        System.out.println(" ");
        System.out.println("== Find faculty by name ==");
        String name = requestString("faculty name");
        name = name.toLowerCase();

        for (Faculty faculty : faculties) {
            if (faculty.getName().toLowerCase().contains(name)) {
                System.out.println(faculty);
                break;
            }
        }
        System.out.println(" ");
    }

    private void createFaculty() {

        System.out.println(" ");
        System.out.println("== Create new faculty ==");

        while (true) {
            String name = requestString("faculty name");

            try{
                Faculty faculty = new Faculty(name);
                Faculty[] newArr = new Faculty[faculties.length + 1];
                for (int i = 0; i < faculties.length; i++) {
                    newArr[i] = faculties[i];
                }
                newArr[faculties.length] = faculty;
                faculties = newArr;
                break;
            }catch (IllegalArgumentException e) {
                System.out.flush();
                System.out.println(e.getMessage());
            }

        }

        System.out.println(" ");
    }

}
