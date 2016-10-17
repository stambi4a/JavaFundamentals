package Problem05.ExtractWords;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args)
    {
        List<String> foundWords = extractWords();
        printFoundWords(foundWords);
    }

    private static void printFoundWords(List<String> foundWords) {
        System.out.println(String.join(" ", foundWords));
    }

    protected static List<String> extractWords() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String pattern = "[a-zA-Z]{2,}";
        Pattern wordPattern = Pattern.compile(pattern);
        Matcher matches = wordPattern.matcher(text);

        List<String> foundWords = new ArrayList<>();

        while(matches.find()) {
            foundWords.add(matches.group());
        }

        return foundWords;
    }
}