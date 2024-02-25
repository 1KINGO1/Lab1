package controls;

import arrays.DataInput;

import java.io.IOException;

public class Control {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private void showInstructions(){
        System.out.println("===== UKMA Control Panel =====");
    }

    public void handleCommands() throws IOException {
        while (true){
            System.out.println("No command :(");
            char command = DataInput.getChar();
        }
    }

    protected char requestCommand(){
        try{
            System.out.print("Enter command: ");

            char command = DataInput.getChar();
            return command;
        }catch (Exception e){
            return "O".charAt(0);
        }

    }

    protected String requestString(String title){
        try{
            System.out.print("Enter " + title + ": ");
            String value = DataInput.getString();
            return value;
        }catch (Exception e){
            return "O";
        }

    }
    protected String requestString(String title, String defaultValue){
        try{
            System.out.print("Enter " + title + ": ");
            String value = DataInput.getString();
            if (value.equals("")){
                return defaultValue;
            }
            return value;
        }catch (Exception e){
            return "O";
        }

    }

    protected int requestInt(String title){
        try{
            System.out.print("Enter " + title + ": ");
            int value = DataInput.getInt();
            return value;
        }catch (Exception e){
            return 0;
        }

    }

    protected int requestInt(String title, int defaultValue){
        try{
            System.out.print("Enter " + title + ": ");
            int value = DataInput.getInt();;

            if (value == 0){
                return defaultValue;
            }

            return value;
        }catch (Exception e){
            return 0;
        }
    }

    protected int requestCourse() {
        int course = 0;

        while (true) {
            try {
                course = requestInt("course", 0);
                if (course < 1 || course > 6) {
                    throw new IllegalArgumentException("Course must be between 1 and 6");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return course;
    }
}
