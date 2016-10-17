package Problem04.LongestIncreasingSequence;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] numbers = inputArray();
        ArrayList<int[]> sequences = findAllSequences(numbers);
        printAllSequences(sequences);
        int[] longestSequence = findLongestSequence(sequences);
        printLongestSequence(longestSequence);
    }

    private static void printLongestSequence(int[] longestSequence) {
        System.out.println("Longest: " + Arrays.toString(longestSequence).replaceAll("\\[|\\]|,", ""));
    }

    private static int[] findLongestSequence(ArrayList<int[]> sequences) {
        Collections.sort(sequences, (int[] first, int[] second)-> second.length - first.length);

        return sequences.get(0);
    }

    private static void printAllSequences(ArrayList<int[]> sequencies) {
        for(int[] sequence : sequencies) {
            System.out.println(Arrays.toString(sequence).replaceAll("\\[|\\]|,", ""));
        }
    }
    private static ArrayList<int[]> findAllSequences(int[] numbers) {
        ArrayList<int[]> sequencies = new ArrayList<>();
        Integer length = numbers.length;
        if (length == 1) {
            sequencies.add(numbers);
        }
        int currentSequenceLength = 1;
        int currentSequenceStartIndex = 0;
        for (int i = 1; i < length; i++) {
           if(numbers[i] <= numbers[i - 1]) {
               int[] sequence = Arrays.copyOfRange(
                       numbers,
                       currentSequenceStartIndex,
                       currentSequenceStartIndex + currentSequenceLength);
               sequencies.add(sequence);
               currentSequenceStartIndex = i;
               currentSequenceLength = 1;
           } else {
               currentSequenceLength++;
           }
           if(i == length - 1) {
               int[] sequence = Arrays.copyOfRange(
                       numbers,
                       currentSequenceStartIndex,
                       currentSequenceStartIndex + currentSequenceLength);
               sequencies.add(sequence);
           }
        }

        return sequencies;
    }

    private static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(" ");
        int[] numbers = Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();

        return numbers;
    }
}