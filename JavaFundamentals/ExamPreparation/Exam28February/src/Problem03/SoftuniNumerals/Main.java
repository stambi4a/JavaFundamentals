package Problem03.SoftuniNumerals;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        convertNumberSoftuniNumericalSystem();
    }

    private static void convertNumberSoftuniNumericalSystem() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String convertedNumber = convertTextToNumber(input);
        BigInteger number = convertStringNumberToNumber(convertedNumber);
        System.out.print(number);
    }

    private static BigInteger convertStringNumberToNumber(String convertedNumber) {
        BigInteger number = BigInteger.valueOf(0);
        int length = convertedNumber.length();
        for(int i = length - 1; i >= 0; i--) {
            BigInteger digit = BigInteger.valueOf(convertedNumber.charAt(i) - '0');
            BigInteger fifths = BigInteger.valueOf(5).pow(length - 1 - i);
            number = number.add(digit.multiply(fifths));
        }

        return number;
    }

    private static String convertMatchToDigit(String matchedString) {
        String number = null;
        switch(matchedString) {
            case "aa" : {
                number = "0";
            }

            break;
            case "aba" : {
                number = "1";
            }

            break;
            case "bcc" : {
                number = "2";
            }

            break;
            case "cc" : {
                number = "3";
            }

            break;
            case "cdc" : {
                number = "4";
            }

            break;
        }

        return number;
    }

    private static String convertTextToNumber(String input) {
        StringBuilder convertedNumber = new StringBuilder();
        String numberPattern = "(aa)|(aba)|(bcc)|(cc)|(cdc)";
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher match = pattern.matcher(input);
        while(match.find()) {
            String matchedString = match.group();
            String number = convertMatchToDigit(matchedString);
            convertedNumber.append(number);
        }

        return convertedNumber.toString();
    }
}
