import java.util.Scanner;

public class FormattingNumbers {
    public static void main (String[] args) {
        try {
            printFormattingNumbers();
        } catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }

    private static void printFormattingNumbers()
    {
        Scanner scanner = new Scanner(System.in);
        Integer firstNumber = scanner.nextInt();
        String firstNumberToBinary = Integer.toBinaryString(firstNumber);

        if(firstNumber < 0 || firstNumber > 500)
        {
            throw new IllegalArgumentException("Integer number should be in the range [0..500]");
        }

        Float secondNumber = scanner.nextFloat();
        Float thirdNumber = scanner.nextFloat();

        System.out.printf("|%-10X", firstNumber);
        System.out.printf(String.format("|%10s", firstNumberToBinary).replace(' ', '0'));
        System.out.printf("|%10.2f", secondNumber);
        System.out.printf("|%-10.3f", thirdNumber);
    }
}
