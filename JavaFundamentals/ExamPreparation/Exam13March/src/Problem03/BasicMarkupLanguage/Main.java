package Problem03.BasicMarkupLanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        processMarkupLanguage();
    }


    private static void processMarkupLanguage() {
        Scanner scanner = new Scanner(System.in);
        List<String> output = new ArrayList<>();
        while(true) {
            String input = scanner.nextLine();
            String commandPattern = "[a-zA-Z]+";
            Pattern pattern = Pattern.compile(commandPattern);
            Matcher match = pattern.matcher(input);
            String command = null;
            if(match.find()) {
                command = match.group(0).toLowerCase();
            }

            if(command.equalsIgnoreCase("stop")) {
                break;
            }

            /*String contentPattern = "(content\\s*=\\s*\")(((\\s*[^\\s\"])+\\s*))";*/
            String contentPattern = "(content\\s*=\\s*\")([^\"]+)";
            pattern = Pattern.compile(contentPattern);
            match = pattern.matcher(input);
            String content = null;
            if(match.find()) {
                content = match.group(2);
            }

            if(content!= null) {
                switch(command) {
                    case "inverse": {
                        inverseContent(content, output);
                    }

                    break;
                    case "reverse": {
                        reverseContent(content, output);
                    }

                    break;
                    case "repeat": {
                        repeatContent(content, input, output);
                    }

                    break;
                    default:
                        break;
                }
            }
        }

        printOutput(output);
    }

    private static void printOutput(List<String> output) {
        int index = 1;
        for(String result : output) {
            System.out.printf("%d. %s%n", index++, result);
        }
    }

    private static void inverseContent(String content, List<String> output) {
        int length = content.length();
        char[] charArray = new char[length];
        for(int i = 0; i < length; i++) {
            Character symbol = content.charAt(i);
            if(symbol <='z' && symbol >='a') {
                symbol = Character.toUpperCase(symbol);
            }

            else if(symbol <='Z' && symbol >='A') {
                symbol = Character.toLowerCase(symbol);
            }

            charArray[i] = symbol;
        }

        String result = String.valueOf(charArray);
        output.add(result);
    }

    private static void reverseContent(String content, List<String> output) {
        output.add(new StringBuffer(content).reverse().toString());
    }

    private static void repeatContent(String content, String input, List<String> output) {
        String countPattern = "(value\\s*=\\s*\")(((\\s*[^\\s\"])+\\s*))";
        Pattern pattern = Pattern.compile(countPattern);
        Matcher match = pattern.matcher(input);
        String value = null;
        if(match.find()) {
            value = match.group(2);
        }
        int count = Integer.parseInt(value);
        for(int i = 0; i < count; i++) {
            output.add(content);
        }
    }
}
