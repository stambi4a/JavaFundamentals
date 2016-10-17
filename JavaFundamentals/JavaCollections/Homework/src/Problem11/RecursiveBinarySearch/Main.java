package Problem11.RecursiveBinarySearch;

import java.util.Arrays;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberToSearch = Integer.parseInt(scanner.nextLine());
        String numberSequence = scanner.nextLine();
        int[] numbers = inputNumbers(numberSequence);
        int endIndex = numbers.length - 1;
        sortArrayOfNumbers(numbers);
        int indexNumber = conductBinarySearch(numbers, 0, endIndex, numberToSearch);
        System.out.println(indexNumber);
    }

    private static int conductBinarySearch(int[] numbers,int startIndex, int endIndex,  int numberToSearch) {
        if(startIndex > endIndex) {
            return -1;
        }

        int middle = (endIndex + startIndex) / 2;
        int result;
        if(numberToSearch < numbers[middle]) {
            endIndex = middle - 1;
            result = conductBinarySearch(numbers, startIndex, endIndex, numberToSearch);
        } else if(numberToSearch > numbers[middle]){
            startIndex = middle + 1;
            result = conductBinarySearch(numbers, startIndex, endIndex, numberToSearch);
        } else {
            while (middle > 0 && numbers[middle - 1] == numberToSearch) {
                middle--;
            }

            result = middle;
        }

        return result;
    }

    private static void sortArrayOfNumbers(int[] numbers) {
        Arrays.sort(numbers);
    }

    private static int[] inputNumbers(String numberSequence) {
        int[] numbers = Arrays.asList(numberSequence
                .split(" ")).stream()
                .mapToInt(Integer::parseInt)
                .toArray();

        return numbers;
    }
}