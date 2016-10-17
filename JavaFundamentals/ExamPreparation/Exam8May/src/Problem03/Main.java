package Problem03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countLines = scanner.nextInt();
        HashMap<String ,HashSet<String>> flushes = new HashMap<>();
        scanner.nextLine();
        flushes.put("s", new HashSet<>());
        flushes.put("h", new HashSet<>());
        flushes.put("d", new HashSet<>());
        flushes.put("c", new HashSet<>());
        int countFlushes = 0;
        for(int i = 0; i < countLines; i++) {
            String input = scanner.nextLine();
            String cardPattern = "([A-Z0-9]+)([a-z])";
            Pattern pattern = Pattern.compile(cardPattern);
            Matcher match = pattern.matcher(input);

            while(match.find()){
                String suit = match.group(2);
                String rank = match.group(1);
                switch(rank) {
                    case "10": {
                        if(flushes.get(suit).contains("10")) {
                            flushes.put(suit, new HashSet<>());
                        } else {

                        }

                        flushes.get(suit).add(rank);
                    }

                    break;

                    case "J": {
                        if(flushes.get(suit).contains("10") && !flushes.get(suit).contains("J")) {
                            flushes.get(suit).add(rank);
                        } else {
                            flushes.put(suit, new HashSet<>());
                        }
                    }

                    break;

                    case "Q": {
                        if(flushes.get(suit).contains("J") && !flushes.get(suit).contains("Q")) {
                            flushes.get(suit).add(rank);
                        } else {
                            flushes.put(suit, new HashSet<>());
                        }
                    }

                    break;

                    case "K": {
                        if(flushes.get(suit).contains("Q") && !flushes.get(suit).contains("K")) {
                            flushes.get(suit).add(rank);
                        } else {
                            flushes.put(suit, new HashSet<>());
                        }
                    }

                    break;

                    case "A": {
                        if(flushes.get(suit).contains("K") && !flushes.get(suit).contains("A")) {
                            flushes.get(suit).add(rank);
                        } else {
                            flushes.put(suit, new HashSet<>());
                        }
                    }

                    break;
                }

                if(flushes.get(suit).size() == 5) {
                    switch(suit) {
                        case "s" :{
                            System.out.println("Royal Flush Found - Spades");
                        }

                        break;

                        case "h" :{
                            System.out.println("Royal Flush Found - Hearts");
                        }

                        break;

                        case "d" :{
                            System.out.println("Royal Flush Found - Diamonds");
                        }

                        break;

                        case "c" :{
                            System.out.println("Royal Flush Found - Clubs");
                        }

                        break;
                    }

                    flushes.put(suit, new HashSet<>());
                    countFlushes++;
                }
            }
        }

        System.out.println("Royal's Royal Flushes - " + countFlushes + ".");
    }
}
