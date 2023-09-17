import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Main mainClass = new Main();
        mainClass.strikeASCII(input);
        mainClass.crossASCII(input);

    }

    public void crossASCII(Scanner input) {
        ArrayList<Object> settings = setUserSettings(input);
        String design = (String)settings.get(0);
        int size = (int)settings.get(1);
        StringBuilder spacer = (StringBuilder)settings.get(2);
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(i == j || (size - i - 1) == j)
                    System.out.print(spacer);
                else
                    System.out.print(design);
            }
            System.out.println();
        }
    }


    public void strikeASCII(Scanner input){
        ArrayList<Object> settings = setUserSettings(input);
        String design = (String)settings.get(0);
        int size = (int)settings.get(1);
        StringBuilder spacer = (StringBuilder)settings.get(2);
        System.out.println("Back or Forward Strike?");
        boolean back = (input.nextLine().toLowerCase().contains("back"));

        // Memory over speed
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if((i == j && back) || ((size - i - 1) == j && !back))
                    System.out.print(spacer);
                else
                    System.out.print(design);
            }
            System.out.println();
        }
    }


    // Scrapyard
    /*public void strikeASCII(Scanner input){
        // Draw left side, print spacer, then draw right side, row by row
        // Never-mind, this method was terrible, review mind thought process first
        ArrayList<Object> settings = setUserSettings(input);
        String design = (String)settings.get(0);
        int size = (int)settings.get(1);
        StringBuilder spacer = (StringBuilder)settings.get(2);

        for(int i = 0; i < size; i++){
            for(int j = i; j < size; j++){
                System.out.print(design+ " ");
            }
            System.out.print(spacer);
            for(int k = size - i; k < size + 1; k++){
                System.out.print(design + " ");
            }
            System.out.println();
        }
    }*/

    public ArrayList<Object> setUserSettings(Scanner input){
        // Use ArrayList to pass multiple values in one method return call
        // First index is design (String), second is size (Integer), third is spacer (StringBuilder)
        ArrayList<Object> settings = new ArrayList<>();
        System.out.println("Input your ASCII design character");
        settings.add(input.nextLine());
        while(true){
            try {
                System.out.println("Input size");
                settings.add(input.nextInt());
                break;
            } catch (Exception ignored) {
                System.out.println("Invalid input, try again!");
                input.nextLine();
            }
        }
        settings.add(new StringBuilder());
        for(int l = 0; l < ((String)settings.get(0)).length(); l++){
            ((StringBuilder)settings.get(2)).append(" ");
        }
        input.nextLine();
        return settings;
    }
}