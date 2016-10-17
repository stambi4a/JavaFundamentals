package Problem08.ExtractAllUniqueWords;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> uniqueWords = extractUniqueWords();
        sortUniqueWords(uniqueWords);
        printUniqueWords(uniqueWords);
    }
    private static void sortUniqueWords(ArrayList<String> uniqueWords) {
        /*uniqueWords.sort((String first, String second) -> first.compareTo(second));*/
        Collections.sort(uniqueWords);
    }

    private static void printUniqueWords(ArrayList<String> uniqueWords) {
        System.out.println(String.join(" ", uniqueWords));
    }

    public static ArrayList<String> extractUniqueWords() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        text = text.toLowerCase();
        HashSet<String> uniqueWords = new HashSet<>();
        String pattern = "[^A-Za-z]";
        String[] words = Arrays.asList(text.split(pattern)).stream().filter(x->!x.isEmpty())
                .collect(Collectors.toList()).toArray(new String[0]);
        int length = words.length;
        for(int i = 0; i < length; i++) {
            uniqueWords.add(words[i]);
        }

        return new ArrayList<String>(uniqueWords);
    }
}