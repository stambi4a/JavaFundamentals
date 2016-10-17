package Problem02.SortArrayWithStreamApi;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numericSequence = scanner.nextLine();
        int[] numbers = createNumericArray(numericSequence);
        String sortType = scanner.next();
        sortNumericArrays(numbers, sortType);
        printNumericArray(numbers);
    }

    private static void printNumericArray(int[] numbers) {
        System.out.println(Arrays.toString(numbers).replaceAll("\\[|\\]|,", ""));
    }

    private static void sortNumericArrays(int[] numbers, String sortType) {
        if(sortType.equals("Ascending")) {
            Arrays.sort(numbers);
        }

        if(sortType.equals("Descending")) {
            Arrays.sort(numbers);
            int length = numbers.length;
            for(int i = 0; i < length/2; i++) {
                int swap = numbers[i];
                numbers[i] = numbers[length - 1 - i];
                numbers[length - 1 - i] = swap;
            }
        }
    }

    private static int[] createNumericArray(String numericSequence) {
        int[] numbers = Arrays
                .asList(numericSequence.split(" "))
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();

        return numbers;
    }
}