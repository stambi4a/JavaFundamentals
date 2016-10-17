package Problem07.MagicExchangeableWords;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean areWordsExchangeable = checkWordsAreExchangeable();
        System.out.println(areWordsExchangeable);
    }

    private static boolean checkWordsAreExchangeable() {
        Scanner scanner = new Scanner(System.in);
        String firstWord = scanner.next();
        String secondWord = scanner.next();
        int length = firstWord.length();
        for(int i = 1; i < length; i++) {
            int index = 0;
            while(index < i) {
                if(firstWord.charAt(index) == firstWord.charAt(i) && secondWord.charAt(index)!= secondWord.charAt(i)) {
                    return false;
                }

                index++;
            }
        }

        return true;
    }
}