package Problem06.StartsAndEndsWithCapitalLetter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> foundWords = extractWordsStartEndWithCapitalLetter();
        printFoundWords(foundWords);
    }

    private static void printFoundWords(List<String> foundWords) {
        System.out.println(String.join(" ", foundWords));
    }

    protected static List<String> extractWordsStartEndWithCapitalLetter() {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");

        String pattern = "\\b[A-Z][a-zA-Z]*[A-Z]\\b";

        List<String> foundWords = Arrays
                .asList(words)
                .stream()
                .filter(x -> x.matches(pattern))
                .collect(Collectors.toList());

        return foundWords;
    }
}