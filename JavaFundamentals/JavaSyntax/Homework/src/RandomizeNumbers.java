import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RandomizeNumbers {
    public static void main(String[] args) {
        randomizeNumbers();
    }

    private static void randomizeNumbers() {
        Scanner scanner = new Scanner(System.in);
        Integer firstNumber = Integer.parseInt(scanner.nextLine());
        Integer secondNumber = Integer.parseInt(scanner.nextLine());
        Integer startNumber = findMin(firstNumber, secondNumber);
        Integer endNumber = findMax(firstNumber, secondNumber);
        Integer[] randomizedNumbers = randomizeAllNumbersBetweenTwoNumbers(startNumber, endNumber);
        printRandomizedNumbers(randomizedNumbers);
    }

    private static Integer findMax(Integer firstNumber, Integer secondNumber) {
        return (firstNumber + secondNumber + Math.abs(firstNumber - secondNumber)) / 2;
    }

    private static Integer findMin(Integer firstNumber, Integer secondNumber) {
        return (firstNumber + secondNumber - Math.abs(firstNumber - secondNumber)) / 2;
    }

    private static Integer[] randomizeAllNumbersBetweenTwoNumbers(Integer firstNumber, Integer secondNumber) {
        Random randGenerator = new Random();
        int length = secondNumber - firstNumber + 1;
        Integer[] numbers = new Integer[length];
        for(int i = 0; i < length; i++) {
            numbers[i] = firstNumber + i;
        }

        for(int i = 0; i < length; i++) {
            Integer randIndex = randGenerator.nextInt(length - i);
            Integer swapNumber = numbers[randIndex];
            numbers[randIndex] = numbers[length - i - 1];
            numbers[length - i - 1] = swapNumber;
        }

        return numbers;
    }

    private static void printRandomizedNumbers(Integer[] randomizedNumbers) {
        System.out.println(Arrays.toString(randomizedNumbers));
    }
}
