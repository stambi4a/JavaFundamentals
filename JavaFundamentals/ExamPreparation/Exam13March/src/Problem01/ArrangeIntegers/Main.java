package Problem01.ArrangeIntegers;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static final int CODE_TABLE_LENGTH = 10;
    public static void main(String[] args) {
        int[] numbers = inputArray();
        String[] codeTable = createEncodeTable();
        TreeMap<String, List<Integer>> encodedNumbers =  encodeArrayOfNumbers(numbers, codeTable);
        printEncodeNumbers(encodedNumbers);
    }

    private static void printEncodeNumbers(TreeMap<String, List<Integer>> encodedNumbers) {
        Stream<Integer> allNumbers = (encodedNumbers.values().stream().flatMap(Collection::stream));
        Integer[] nums = allNumbers.toArray(size->new Integer[size]);
        System.out.println(Arrays.toString(nums).replaceAll("[\\[\\]]", ""));
    }

    private static TreeMap<String, List<Integer>> encodeArrayOfNumbers(int[] numbers, String[] codeTable) {
        TreeMap<String, List<Integer>> numbersEncodedNumbers = new TreeMap<>();
        for(int i = 0; i < numbers.length; i++) {
            String encodedNumber = encodeNumber(numbers[i], codeTable);
            numbersEncodedNumbers.putIfAbsent(encodedNumber, new ArrayList<>());
            numbersEncodedNumbers.get(encodedNumber).add(numbers[i]);
        }

        return numbersEncodedNumbers;
    }

    private static String encodeNumber(int number, String[] codeTable) {
        if(number == 0) {
            return codeTable[0];
        }

        int digitCount = (int)Math.log10(number) + 1;
        String[] encodedNumber = new String[digitCount];
        for(int i = 0; i < digitCount; i++) {
            encodedNumber[digitCount - 1 - i] = codeTable[number % 10];
            number /= 10;
        }

        return String.join("",encodedNumber);
    }

    private static String[] createEncodeTable() {
        String[] codeTable = new String[CODE_TABLE_LENGTH];
        codeTable[0] = "zero";
        codeTable[1] = "one";
        codeTable[2] = "two";
        codeTable[3] = "three";
        codeTable[4] = "four";
        codeTable[5] = "five";
        codeTable[6] = "six";
        codeTable[7] = "seven";
        codeTable[8] = "eight";
        codeTable[9] = "nine";

        return codeTable;
    }

    private static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.asList(scanner.nextLine().split(", "))
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();

        return numbers;
    }
}
