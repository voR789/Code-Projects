import java.util.Arrays;
import java.util.Scanner;

public class ArraySpread {

    private static final int[] arr = new int[20];
    private static int[] input;
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        prompt();
        showFill();
    }

    private static void fill() {
        if (arr.length % input.length == 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = input[i / (arr.length / input.length)];
            }
            return;
        }

        int extras = arr.length % input.length;
        double key = (arr.length - extras) / (double) extras;
        double keyAdd = key;
        double shifter = key / 2;
        int count = 0;
        int arrCount = 0;

        for (int i = 0; i < arr.length; i++) {
            int current = input[(count++ / (arr.length / input.length))];
            arr[arrCount++] = current;

            if ((Math.round(keyAdd - shifter)) == count) {
                i++;
                arr[arrCount++] = current;
                keyAdd += key;
            }
        }
    }

    private static void prompt(){
        System.out.print("Enter number of inputs: ");
        int len = scan.nextInt();
        input = new int[len];
        for (int i = 0; i < len; i++) {
            input[i] = i + 1;
        }
    }

    private static void showFill(){
        System.out.println("Before: " + Arrays.toString(input));
        fill();
        System.out.println("After: " + Arrays.toString(arr));
    }
}
