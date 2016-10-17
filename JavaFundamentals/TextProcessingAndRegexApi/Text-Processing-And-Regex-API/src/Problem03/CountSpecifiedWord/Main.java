package Problem03.CountSpecifiedWord;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        int wordCount = countWordOccurrences();
        System.out.println(wordCount);
    }

    private static int countWordOccurrences() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String word = scanner.nextLine();
        String wordPattern = String.format("(?i)\\b%s\\b", word);
        Pattern pattern = Pattern.compile(wordPattern);
        Matcher matches = pattern.matcher(text);
        int wordCount = 0;
        while(matches.find()) {
            wordCount++;
        }

        return wordCount;
    }
}
