package arrays.arrays;

import java.util.Random;

public class RandomData {

    public static int[] generateArray(int size){
        int[] arr = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
                arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public static int[][] generateMatrix(int size){
        int[][] arr = new int[size][size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                arr[i][j] = random.nextInt(10050);
            }
        }
        return arr;
    }

}
