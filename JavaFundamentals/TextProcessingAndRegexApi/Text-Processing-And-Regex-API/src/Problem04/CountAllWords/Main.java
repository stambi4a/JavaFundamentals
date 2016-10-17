package Problem04.CountAllWords;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)
    {
        int wordCount = countWords();
        System.out.println(wordCount);
    }

    private static int countWords() {
        Scanner scanner = new Scanner(System.in);
        String pattern = "[^a-zA-Z]";
        String[] words = Arrays.asList(scanner.nextLine().split(pattern)).stream().filter(x->!x.isEmpty())
                .collect(Collectors.toList()).toArray(new String[0]);

        return words.length;
    }
}