package Problem02.SoftuniDefenseSystem;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        testSoftuniDefenseSystem();
    }

    private static int newLineTest(String input) {
        int alcoholQuantityNormalSystem = 0;
//        String alcoholTryBringTest = "([A-Z][a-z]*)[^a-zA-Z]+(([A-Z][a-z]+[A-Z])|[A-Z])[^a-zA-Z0-9]+(\\d{1,4})L[^A-Z]*";
        String alcoholTryBringTest = "([A-Z][a-z]+).*?(([A-Z][a-z]*[A-Z])).*?(\\d{1,7})L";
        Pattern pattern = Pattern.compile(alcoholTryBringTest);
        Matcher match = pattern.matcher(input);
        while(match.find()) {
            String guest = match.group(1);
            String alcohol = match.group(2).toLowerCase();
            String alcoholQuantity = match.group(4);
            int quantity = Integer.parseInt(alcoholQuantity);

            System.out.printf("%s brought %d liters of %s!%n", guest, quantity, alcohol);
            alcoholQuantityNormalSystem += quantity;
        }

        return alcoholQuantityNormalSystem;
    }

    private static void testSoftuniDefenseSystem() {
        Scanner scanner = new Scanner(System.in);
        int broughtAlcohol = 0;
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("OK KoftiShans")) {
                break;
            }

            broughtAlcohol += newLineTest(input);
        }

        Double alcoholQuantitySoftuniSystem = broughtAlcohol/1000d;
        System.out.printf("%.3f softuni liters", alcoholQuantitySoftuniSystem);
    }
}
