import java.util.Scanner;

public class BaseSevenToDecimal {
    public static void main(String[] args) {
        convertSevenBaseToDecimal();
    }

    private static void convertSevenBaseToDecimal() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                Integer numberInSevenBase = Integer.parseInt(scanner.nextLine());
                Integer decimalNumber = 0;
                Integer sevens = 1;
                while(numberInSevenBase > 0)
                {
                    decimalNumber += (numberInSevenBase % 10) * sevens;
                    sevens *= 7;
                    numberInSevenBase /= 10;
                }
                System.out.println(decimalNumber);
            } catch (NumberFormatException nfe){
                break;
            }
        }
    }
}
