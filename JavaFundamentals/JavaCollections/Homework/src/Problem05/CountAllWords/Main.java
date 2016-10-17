package Problem05.CountAllWords;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        countWords();
    }

    private static void countWords() {
        Scanner scanner = new Scanner(System.in);
        String pattern = "[^a-zA-Z]";
        /*String pattern = "\\s|[0-9]|_|,|\\.|\\?|!|-|\\(|\\)|'|\"|\\[|\\]";*/
        String[] words = Arrays.asList(scanner.nextLine().split(pattern)).stream().filter(x->!x.isEmpty())
                .collect(Collectors.toList()).toArray(new String[0]);
        System.out.println(words.length);
    }
}
