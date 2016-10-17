package Problem03.LargestSequenceOfEqualStrings;

import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        String[] strings = inputArray();
        String largestSequence = findLargestSequencesOfStrings(strings);
        System.out.println(largestSequence);
    }

    private static String findLargestSequencesOfStrings(String[] strings) {
        int length = strings.length;
        if(length == 1) {
            return strings[0];
        }
        int largestSequenceLength = 1;
        int currentSequenceLength = 1;
        String sequenceString = strings[0];
        for(int i = 1; i < length; i++) {
            if(strings[i].equals(strings[i - 1])) {
                currentSequenceLength++;
                if(i == length - 1 && currentSequenceLength > largestSequenceLength) {
                    largestSequenceLength = currentSequenceLength;
                    sequenceString = strings[i];
                }
            } else {
                if(currentSequenceLength > largestSequenceLength) {
                    largestSequenceLength = currentSequenceLength;
                    sequenceString = strings[i - 1];
                }

                currentSequenceLength = 1;
            }
        }

        String largestSequence = String.join(" ", Collections.nCopies(largestSequenceLength, sequenceString));

        return largestSequence;
    }

    private static String[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(" ");

        return strings;
    }
}