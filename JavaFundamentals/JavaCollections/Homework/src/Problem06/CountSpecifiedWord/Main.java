package Problem06.CountSpecifiedWord;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        countSpecificWord();
    }

    private static void countSpecificWord() {
        Scanner scanner = new Scanner(System.in);
        String textToSearch = scanner.nextLine();
        String wordToSearch = scanner.next();
        int wordCount = 0;
        Pattern pattern = Pattern.compile(String.format("(?i)\\b%s\\b", wordToSearch));
        Matcher match = pattern.matcher(textToSearch);
        while(match.find()) {
            wordCount++;
        }

        System.out.println(wordCount);
    }
}