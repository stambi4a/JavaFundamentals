import java.util.Locale;
import java.util.Scanner;

public class CalculateExpression {
    private static final int FloatingNumbersCount = 3;
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        calculateMathExpressions();
    }

    private static void calculateMathExpressions() {
        Scanner scanner = new Scanner(System.in);
        Double[] numbers = new Double[FloatingNumbersCount];
        for(int i = 0; i < FloatingNumbersCount; i++)
        {
            numbers[i]= scanner.nextDouble();
        }

        Double firstExpression = calculateFirstExpression(numbers);
        System.out.printf("F1 result: %.2f;", firstExpression);
        Double secondExpression = calculateSecondExpression(numbers);
        System.out.printf("F2 result: %.2f;" ,secondExpression);

        Double result = getAverageNumbers(numbers) - (firstExpression + secondExpression) / 2;
        System.out.printf("Diff: %.2f", result);

    }

    private static Double getAverageNumbers(Double[] vararg) {
        Double sum = 0.0d;
        for(int i = 0;i < vararg.length; i++)
        {
            sum += vararg[i];
        }

        Double average = sum / vararg.length;

        return average;
    }

    private static Double calculateFirstExpression(Double[] vararg){
        Double expressionPower = (vararg[0] + vararg[1] + vararg[2])/ Math.sqrt(vararg[2]);
        Double expressionBase = (Math.pow(vararg[0], 2) + Math.pow(vararg[1], 2)) /
                (Math.pow(vararg[0],2) - Math.pow(vararg[1], 2));
        Double expressionResult = Math.pow(expressionBase, expressionPower);

        return expressionResult;
    }

    private static Double calculateSecondExpression(
            Double[] vararg){
        Double expressionPower = vararg[0] - vararg[1];
        Double expressionBase = (Math.pow(vararg[0], 2) + Math.pow(vararg[1], 2) - Math.pow(vararg[2], 3));
        Double expressionResult = Math.pow(expressionBase, expressionPower);

        return expressionResult;
    }
}
