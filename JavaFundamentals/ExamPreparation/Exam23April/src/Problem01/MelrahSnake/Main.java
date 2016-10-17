package Problem01.MelrahSnake;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringCharsPattern = scanner.nextLine();
        String key = scanner.nextLine();
        shakePatterns(stringCharsPattern, key);
    }

    private static void shakePatterns(String stringCharsPattern, String key) {
        while(true)
        {
            int length = key.length();
            int indexFirst = stringCharsPattern.indexOf(key);
            int indexLast = stringCharsPattern.lastIndexOf(key);
            StringBuilder newStringCharsPattern = new StringBuilder();
            if (indexFirst > -1 && length > 0 && indexLast - indexFirst >= length) {
                newStringCharsPattern.append(stringCharsPattern.substring(0, indexFirst));
                newStringCharsPattern.append(stringCharsPattern.substring(indexFirst + length, indexLast));
                newStringCharsPattern.append(stringCharsPattern.substring(indexLast + length, stringCharsPattern.length()));
                stringCharsPattern = newStringCharsPattern.toString();
                System.out.println("Shaked it.");
            } else {
                System.out.println("No shake.");
                System.out.println(stringCharsPattern);
                return;
            }

            if (length <= 1) {
                System.out.println("No shake.");
                System.out.println(stringCharsPattern);
                return;
            }
            int indexCharToRemove = length / 2;
            key = key.substring(0, indexCharToRemove) +
                    key.substring(indexCharToRemove + 1, length);
        }
    }
}
