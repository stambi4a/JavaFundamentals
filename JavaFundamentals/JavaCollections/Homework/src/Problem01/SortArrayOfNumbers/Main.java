package Problem01.SortArrayOfNumbers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Integer[] numbers = inputArray();
        sortArrayOfNumbers(numbers);
        printArrayOfNumbers(numbers);
    }

    private static void printArrayOfNumbers(Integer[] numbers) {
        System.out.println(Arrays.toString(numbers).replaceAll("\\[|\\]|,", ""));
    }
    private static void sortArrayOfNumbers(Integer[] numbers) {
        Arrays.sort(numbers);
    }

    private static Integer[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        int arrayLength = scanner.nextInt();
        Integer[] numbers = new Integer[arrayLength];
        for(int i = 0; i < arrayLength; i++) {
            numbers[i] = scanner.nextInt();
        }

        return numbers;
    }
}
