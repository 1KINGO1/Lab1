package controls;

import arrays.DataInput;

import java.io.IOException;

public class Control {

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
}
