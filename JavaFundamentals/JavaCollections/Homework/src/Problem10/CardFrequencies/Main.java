package Problem10.CardFrequencies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] cards = dealCards();
        int cardsCount = cards.length;
        HashMap<String, Integer> cardFrequencies = countCardFrequencies(cards);
        printCardFrequencies(cardFrequencies, cardsCount);
    }

    private static void printCardFrequencies(HashMap<String, Integer> cardFrequencies, int cardsCount) {
        for(Map.Entry<String, Integer> cardFrequency: cardFrequencies.entrySet()) {
            System.out.printf("%s -> %.2f%%%n", cardFrequency.getKey(), cardFrequency.getValue()/(float)cardsCount * 100);
        }
    }

    private static HashMap<String, Integer>  countCardFrequencies(String[] cards) {
        HashMap<String, Integer> cardFrequencies = new HashMap<>();
        for(String card : cards) {
            String cardRank = card.substring(0, card.length() - 1);
            if(!cardFrequencies.containsKey(cardRank)) {
                cardFrequencies.put(cardRank, 0);
            }

            cardFrequencies.put(cardRank, cardFrequencies.get(cardRank) + 1);
        }

        return cardFrequencies;
    }

    private static String[] dealCards() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] cards = text.split(" ");

        return cards;
    }
}
