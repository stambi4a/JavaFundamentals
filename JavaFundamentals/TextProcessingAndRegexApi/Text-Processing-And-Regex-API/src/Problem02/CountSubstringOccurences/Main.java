package Problem02.CountSubstringOccurences;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int susbtringCount = countSubstringOccurrences();
        System.out.println(susbtringCount);
    }

    private static int countSubstringOccurrences() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toLowerCase();
        String substring = scanner.nextLine().toLowerCase();
        int startIndex = 0;
        int substringCount = 0;
        while(true) {
            startIndex = text.indexOf(substring, startIndex);
            if(startIndex++ == -1) {
                break;
            }

            substringCount++;
        }

        return substringCount;
    }
}