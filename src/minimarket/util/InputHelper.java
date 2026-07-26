package minimarket.util;
 
import java.util.Scanner;
 
public class InputHelper {
    private Scanner scanner = new Scanner(System.in);
 
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
 
    public int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }
 
    public double getDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }
}