package Problem07.CombineListsOfLetters;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> combinedList = combineListsOfLetters();
        printCharacterList(combinedList);
    }

    private static void printCharacterList(ArrayList<Character> combinedList) {
        String result = combinedList.toString().replaceAll("\\[|\\]|,", "");
        System.out.println(result);
    }

    private static ArrayList<Character> combineListsOfLetters() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> firstList = new ArrayList<>();
        String input = scanner.nextLine();
        for(int i = 0; i < input.length(); i = i + 2) {
            firstList.add(input.charAt(i));
        }

        ArrayList<Character> secondList = new ArrayList<>();
        input = scanner.nextLine();
        for(int i = 0; i < input.length(); i = i + 2) {
            secondList.add(input.charAt(i));
        }

        ArrayList<Character> combinedList = new ArrayList<>(firstList);
        int secondListSize = secondList.size();
        int firstListSize = firstList.size();
        for(int i = 0; i < secondListSize; i++) {
            Character character = secondList.get(i);
            for(int j = 0; j < firstListSize; j++) {
                if(character == firstList.get(j)) {
                    break;
                }
                if(j == firstListSize - 1) {
                    combinedList.add(character);
                }
            }
        }

        return combinedList;
    }
}