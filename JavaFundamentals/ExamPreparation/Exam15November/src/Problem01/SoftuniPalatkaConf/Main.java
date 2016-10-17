package Problem01.SoftuniPalatkaConf;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        organizePalatkaConference();
    }

    private static void organizePalatkaConference() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int people = Integer.parseInt(input);
        int[] foodBeds = new int[2];
        input = scanner.nextLine();
        int inputLines = Integer.parseInt(input);
        int index = 1;
        while(index <= inputLines) {
            input = scanner.nextLine();
            addFoodBeds(input, foodBeds);
            index++;
        }

        printRecapitulaion(foodBeds, people);
    }

    private static void printRecapitulaion(int[] foodBeds, int people) {
        int sleepPlaces = foodBeds[0] - people;
        if(sleepPlaces < 0) {
            System.out.printf("Some people are freezing cold. Beds needed: %d%n", -sleepPlaces);
        } else {
            System.out.printf("Everyone is happy and sleeping well. Beds left: %d%n", sleepPlaces);
        }

        int foodSupplies = foodBeds[1] - people;
        if(foodSupplies < 0) {
            System.out.printf("People are starving. Meals needed: %d%n", -foodSupplies);
        } else {
            System.out.printf("Nobody left hungry. Meals left: %d%n", foodSupplies);
        }
    }

    private static void addFoodBeds(String input, int[] foodBeds) {
        String[] params = input.split(" ");
        switch(params[0]) {
            case "tents" : {
                int multiplier = 0;
                switch(params[2]) {
                    case "firstClass" : {
                        multiplier = 3;
                    }

                    break;
                    case "normal" : {
                        multiplier = 2;
                    }

                    break;
                    default : break;
                }

                foodBeds[0] += Integer.parseInt(params[1]) * multiplier;
            }
            break;

            case "rooms" : {
                int multiplier = 0;
                switch(params[2]) {
                    case "single" : {
                        multiplier = 1;
                    }

                    break;
                    case "double" : {
                        multiplier = 2;
                    }

                    break;
                    case "triple" : {
                        multiplier = 3;
                    }

                    break;
                    default : break;
                }

                foodBeds[0] += Integer.parseInt(params[1]) * multiplier;
            }
            break;

            case "food" : {
                int multiplier = 0;
                switch(params[2]) {
                    case "musaka" : {
                        multiplier = 2;
                    }

                    break;
                    case "zakuska" : {
                        multiplier = 0;
                    }

                    break;

                    default : break;
                }

                foodBeds[1] += Integer.parseInt(params[1]) * multiplier;
            }
            break;
        }
    }
}
