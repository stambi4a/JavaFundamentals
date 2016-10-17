import java.util.Arrays;
import java.util.Scanner;

public class CharacterMultiplier {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = new String[2];
        for(int i = 0; i < 2; i++) {
            strings[i] = scanner.next();
        }

        sumStringCharValues(strings);
    }

    private static void sumStringCharValues(String[] strings) {
        Arrays.sort(strings);
        Integer characterValueSum = 0;
        Integer shorterStringLength = strings[1].length();
        for(int i = 0; i < shorterStringLength; i++) {
            Character charShorterString = strings[1].charAt(i);
            Character charLongerString = strings[0].charAt(i);
            Integer characterMultiplication = charShorterString * charLongerString;
            characterValueSum += characterMultiplication;

        }

        Integer longerStringLength = strings[0].length();
        for(int i = shorterStringLength; i < longerStringLength; i++) {
            Character charLongerString = strings[0].charAt(i);
            characterValueSum += charLongerString;
        }

        System.out.println(characterValueSum);
    }
}
