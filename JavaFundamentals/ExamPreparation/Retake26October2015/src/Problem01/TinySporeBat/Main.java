package Problem01.TinySporeBat;

import java.util.Scanner;

public class Main {
    private static final int INITIAL_HEALTH = 5800;
    private static final int LIFEBLOOM_HEAL = 200;
    private static final int SPOREBAT_PRICE = 30;
    private static final String DIED_MESSAGE = "Died. Glowcaps: %d";
    private static final String HEALTH_LEFT_MESSAGE = "Health left: %d";
    private static final String CAN_BUY_SPOREBAT_MESSAGE = "Bought the sporebat. Glowcaps left: %d";
    private static final String CANNOT_BUY_SPOREBAT_MESSAGE = "Safe in Sporeggar, but another %d Glowcaps needed.";
    public static void main(String[] args) {
        collectGlowcaps();
    }

    private static void collectGlowcaps() {
        Scanner scanner = new Scanner(System.in);
        int[] params = new int[2];
        params[0] = INITIAL_HEALTH;
        params[1] = 0;
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("Sporeggar")) {
                break;
            }


            boolean isAlive = tryCollectGlowcaps(input, params);
            if(!isAlive) {
                break;
            }
        }

        printResult(params[0], params[1]);
    }

    private static void printResult(Integer health, Integer glowcaps) {
        if(health <= 0) {
            System.out.printf(DIED_MESSAGE, glowcaps);
            return;
        }
        if(health > 0) {
            System.out.printf(HEALTH_LEFT_MESSAGE, health);
            System.out.println();
        }

        if(glowcaps < SPOREBAT_PRICE) {
            System.out.printf(CANNOT_BUY_SPOREBAT_MESSAGE, SPOREBAT_PRICE - glowcaps);
        } else {
            System.out.printf(CAN_BUY_SPOREBAT_MESSAGE, glowcaps - SPOREBAT_PRICE);;
        }
    }

    private static boolean tryCollectGlowcaps(String input, int[] params) {
        int length = input.length();
        for(int i = 0; i< length - 1; i++) {
            Character symbol = input.charAt(i);
            if (symbol.equals('L')) {

                params[0] += LIFEBLOOM_HEAL;
                continue;
            }

            int damage = symbol;
            params[0] -= damage;
            if(params[0] <= 0) {
                return false;
            }
        }

        params[1] += input.charAt(length - 1) - '0';
        return true;
    }
}