package Problem01;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String key = scanner.nextLine();
        int indexShaking = 1;
        while(input.length() > 0) {
            Pattern pattern = Pattern.compile(key);
            Matcher match = pattern.matcher(input);
            int index = 0;
            while(match.find()) {
                index++;
            }

            if(index >= 2) {
                match.reset();
                if(match.find()) {
                    input = input.replaceFirst(match.group(), "");
                    int lastIndex = input.lastIndexOf(key);
                    input = input.substring(0, lastIndex) + input.substring(lastIndex + key.length());
                }

                System.out.println("Shaked it.");
                if(indexShaking == 1) {
                    int indexRemove = key.length() /2;
                    key = key.substring(0, indexRemove) + key.substring(indexRemove + 1, key.length());
                }
                indexShaking++;
            } else {
                break;
            }
        }

        System.out.println("No shake.");
        System.out.println(input);
    }

}
