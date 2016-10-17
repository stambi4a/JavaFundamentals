package Problem01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = scanner.nextLine().split("\\s");
        int length = names.length;
        List<String> royalists = new ArrayList<>();
        List<String> nonRoyalists = new ArrayList<>();
        int countRoyalists = 0;
        for(int i = 0; i < length; i++) {
            int sumChars = 0;
            int nameLength = names[i].length();
            for(int j = 0; j < nameLength; j++) {
                sumChars += names[i].charAt(j);
                sumChars %= 26;
            }

            if(sumChars - ('R' - 'A' + 1) == 0) {
                countRoyalists++;
                royalists.add(names[i]);
            } else {
                nonRoyalists.add(names[i]);
            }
        }

        if(countRoyalists >= length - countRoyalists) {
            System.out.println("Royalists - " + countRoyalists);
            System.out.println(String.join("\n", royalists));
            System.out.println("All hail Royal!");

        } else {
            System.out.println(String.join("\n", nonRoyalists));
            System.out.println("Royalism, Declined!");
        }
    }
}
