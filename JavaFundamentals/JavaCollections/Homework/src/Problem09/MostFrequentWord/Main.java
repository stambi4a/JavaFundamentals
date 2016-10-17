package Problem09.MostFrequentWord;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> uniqueWords = countUniqueWords();
        ArrayList<Map.Entry<String, Integer>> mostFrequentWords = findMostFrequentWords(uniqueWords);
        printMostFrequentWord(mostFrequentWords);
    }

    private static void printMostFrequentWord (ArrayList<Map.Entry<String, Integer>> mostFrequentWords) {
        for(Map.Entry<String, Integer> entry : mostFrequentWords) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
    private static ArrayList<Map.Entry<String, Integer>> findMostFrequentWords(HashMap<String, Integer> uniqueWords) {
        ArrayList<Map.Entry<String, Integer>> wordsByCount = new ArrayList<>(uniqueWords.entrySet());
        Collections.sort(wordsByCount, (Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) ->
        second.getValue() - first.getValue());

        ArrayList<Map.Entry<String, Integer>> mostFrequentWords = new ArrayList<>();
        int highestCount = wordsByCount.get(0).getValue();
        for(int i = 0; i < wordsByCount.size(); i++) {
            Map.Entry<String, Integer> entry = wordsByCount.get(i);
            if(entry.getValue() == highestCount) {
                mostFrequentWords.add(entry);
            } else {
                break;
            }
        }

        Collections.sort(mostFrequentWords, (Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) ->
                first.getKey().compareTo(second.getKey()));

        return mostFrequentWords;
    }

    public static HashMap<String, Integer> countUniqueWords() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        text = text.toLowerCase();
        HashMap<String, Integer> uniqueWords = new HashMap<>();
        String pattern = "[^A-Za-z]";
        String[] words = Arrays.asList(text.split(pattern)).stream().filter(x->!x.isEmpty())
                .collect(Collectors.toList()).toArray(new String[0]);
        int length = words.length;
        for(int i = 0; i < length; i++) {
            if(!uniqueWords.containsKey(words[i])) {
                uniqueWords.put(words[i], 0);
            }

            uniqueWords.put(words[i], uniqueWords.get(words[i]) + 1);
        }

        return uniqueWords;
    }
}
