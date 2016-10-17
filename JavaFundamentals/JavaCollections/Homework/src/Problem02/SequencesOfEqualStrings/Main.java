package Problem02.SequencesOfEqualStrings;

import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        String[] strings = inputArray();
        printSequencesOfStrings(strings);
    }

    private static void printSequencesOfStrings(String[] strings) {
        int length = strings.length;
        int sequenceLength = 1;

        for(int i = 0; i < length - 1; i++) {
            if(strings[i + 1].equals(strings[i])) {
                sequenceLength++;
            } else {
                System.out.println(String.join(" ", Collections.nCopies(sequenceLength, strings[i])));
                sequenceLength = 1;
            }
        }
        if(sequenceLength == 1) {
            System.out.println(strings[length - 1]);
        } else {
            System.out.println(String.join(" ", Collections.nCopies(sequenceLength, strings[length - 1])));
        }
    }

    private static String[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(" ");

        return strings;
    }
}