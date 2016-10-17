import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class GetFirstOddOrEvenElements {
    public static void main (String[] args) {
        try {
            GetFirstOddEvenNumbers();
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }

    private static void GetFirstOddEvenNumbers() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        Collection<Integer> numbers = createCollection(input);
        String[] commandParams = scanner.nextLine().split(" ");
        if(!commandParams[0].equals("Get")) {
            throw new IllegalArgumentException("Invalid command");
        }

        Integer firstNumbersCount = Integer.parseInt(commandParams[1]);
        String numbersByDivisionByTwo = commandParams[2];
        Collection<Integer> firstNumbers = new ArrayList<>();
        if(numbersByDivisionByTwo.equals("even")) {
            firstNumbers = getFirstEvenNumbers(numbers, firstNumbersCount);
        } else if(numbersByDivisionByTwo.equals("odd")) {
            firstNumbers = getFirstOddNumbers(numbers, firstNumbersCount);
        } else {
            throw new IllegalArgumentException("Invalid number type-by-division-by-two.");
        }

        printFirstOddOrEvenNumbers(firstNumbers);
    }

    private static void printFirstOddOrEvenNumbers(Collection<Integer> firstNumbers) {
        System.out.println(Arrays.toString(firstNumbers.toArray()));
    }

    private static Collection<Integer> createCollection(String[] input) {
        Collection<Integer> numbers = new ArrayList<>();
        int length = input.length;
        for(int i = 0; i < length ; i++) {

            numbers.add(Integer.parseInt(input[i]));
        }

        return numbers;
    }

    private static Collection<Integer> getFirstEvenNumbers(Collection<Integer> numbers, Integer firstNumbersCount) {
        Collection<Integer> firstEvenNumbers = new ArrayList<>();
        int index = 0;
        for(Integer number : numbers) {
            if(index == firstNumbersCount) {
                return firstEvenNumbers;
            }
            if(number % 2 == 0) {
                firstEvenNumbers.add(number);
            }
        }

        return firstEvenNumbers;
    }

    private static Collection<Integer> getFirstOddNumbers(Collection<Integer> numbers, Integer firstNumbersCount) {
        Collection<Integer> firstOddNumbers = new ArrayList<>();
        int index = 0;
        for(Integer number : numbers) {
            if(index == firstNumbersCount) {
                return firstOddNumbers;
            }
            if(number % 2 == 1) {
                firstOddNumbers.add(number);
            }
        }

        return firstOddNumbers;
    }
}
