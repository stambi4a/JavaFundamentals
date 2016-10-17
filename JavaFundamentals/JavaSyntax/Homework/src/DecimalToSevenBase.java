import java.util.Scanner;

public class DecimalToSevenBase {
    public static void main(String[] args) {
        convertNumberToSevenBase();
    }

    private static void convertNumberToSevenBase() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                Integer decimalNumber = Integer.parseInt(scanner.nextLine());
                String sevenBaseNumber = Integer.toString(decimalNumber, 7);
                System.out.println(sevenBaseNumber);
            } catch (NumberFormatException nfe){
                break;
            }
        }
    }
}
