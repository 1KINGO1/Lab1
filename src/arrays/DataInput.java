package arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class DataInput {

    public static Long getLong() throws IOException{
        String s = getString();
        Long value = Long.valueOf(s);
        return value;
    }

    public static char getChar() throws IOException{
        String s = getString();
        return s.charAt(0);
    }

    public static Integer getInt(){
        String s = "";
        Integer value = 0;
        while (true) {
            try {
                s = getString();
                value = Integer.valueOf(s);
                break;
            } catch (NumberFormatException e) {
                return 0;
            } catch (IOException e) {
                return 0;
            }
        }
        return value;

    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static int[] getArray(){
        System.out.println("Enter array size: ");
        int size = getInt();
        int[] arr = new int[size];
        System.out.println("Enter array elements: ");
        for(int i = 0; i < size; i++){
            arr[i] = getInt();
        }
        return arr;
    }

    public static int[][] getMatrix(){
        System.out.println("Enter matrix size: ");
        int size = getInt();
        int[][] arr = new int[size][size];
        System.out.println("Enter matrix elements: ");
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                arr[i][j] = getInt();
            }
        }
        return arr;
    }

}