package Problem03.TheHeiganDance;

import java.util.Scanner;

public class Main {
    private static final Double HEIGAN_HEALTH = 3000000d;
    private static final int PLAYER_HEALTH = 18500;
    private static final int MATRIX_SIZE = 15;
    private static final int PLAGUE_CLOUD_DAMAGE_PER_TURN = 3500;
    private static final int ERUPTION_DAMAGE = 6000;

    private static final String HEIGAN_DEFEATED_MESSAGE = "Heigan: Defeated!";
    private static final String PLAYER_DEFEATED_MESSAGE = "Player: Killed by %s";
    private static final String HEIGAN_NOT_DEFEATED_MESSAGE = "Heigan: %.2f";
    private static final String PLAYER_NOT_DEFEATED_MESSAGE = "Player: %d";
    private static final String POSITION_MESSAGE = "Final position: %d, %d";

    private static boolean plagueCloudUsed = false;
    private static String lastSpellUsed = "Plague Cloud";
    private static int playerHealth = PLAYER_HEALTH;
    private static Double heiganHealth = HEIGAN_HEALTH;

    public static void main(String[] args) {
        fightHeigan();
    }

    private static void fightHeigan() {
        Scanner scanner = new Scanner(System.in);
        Double playerDamage = Double.parseDouble(scanner.nextLine());
        int[] playerCoordinates = new int[2];
        playerCoordinates[0] = 7;
        playerCoordinates[1] = 7;
        while(true) {
            String input = scanner.nextLine();
            String[] params = input.split(" ");
            playRound(params, playerCoordinates, playerDamage);
            if(playerHealth <= 0 || heiganHealth <= 0) {
                break;
            }
        }

        printResult(playerCoordinates);
    }

    private static void printResult(int[] playerCoordinates) {
        if(heiganHealth <= 0) {
            System.out.printf(HEIGAN_DEFEATED_MESSAGE);
        } else {
            System.out.printf(HEIGAN_NOT_DEFEATED_MESSAGE, heiganHealth);
        }

        System.out.println();

        if(playerHealth <= 0) {
            System.out.printf(PLAYER_DEFEATED_MESSAGE, lastSpellUsed);
        } else {
            System.out.printf(PLAYER_NOT_DEFEATED_MESSAGE, playerHealth);
        }

        System.out.println();

        System.out.printf(POSITION_MESSAGE, playerCoordinates[0], playerCoordinates[1]);
    }

    private static void playRound(String[] params, int[] playerCoordinates, Double playerDamage) {
        heiganHealth -= playerDamage;
        if(plagueCloudUsed) {
            plagueCloudUsed = false;
            playerHealth -= PLAGUE_CLOUD_DAMAGE_PER_TURN;
        }
        if(heiganHealth <= 0 || playerHealth <= 0) {
            return;
        }

        int row = Integer.parseInt(params[1]);
        int column = Integer.parseInt(params[2]);

        if(Math.abs(playerCoordinates[0] - row) > 1 || Math.abs(playerCoordinates[1] - column) > 1) {
            return;
        }

        boolean isHit = false;
        if(row == playerCoordinates[0] && column == playerCoordinates[1]) {
            isHit = true;
        }

        if(!isHit) {
            int upCoordinate = playerCoordinates[0] - 1;
            if(upCoordinate >= 0 && Math.abs(upCoordinate - row) > 1) {
                playerCoordinates[0] = upCoordinate;
                return;
            } else {
                isHit = true;
            }

            if(isHit) {
                int rightCoordinate = playerCoordinates[1] + 1;
                if (rightCoordinate <= MATRIX_SIZE - 1 && Math.abs(rightCoordinate - column) > 1) {
                    playerCoordinates[1] = rightCoordinate;
                    return;
                }

                int downCoordinate = playerCoordinates[0] + 1;
                if (downCoordinate <= MATRIX_SIZE - 1 && Math.abs(downCoordinate - row) > 1) {
                    playerCoordinates[0] = downCoordinate;
                    return;
                }

                int leftCoordinate = playerCoordinates[1] - 1;
                if (leftCoordinate >= 0 && Math.abs(leftCoordinate - column) > 1) {
                    playerCoordinates[1] = leftCoordinate;
                    return;
                }
            }
        }

        String spell = params[0];
        if(spell.equals("Cloud")) {
            lastSpellUsed = "Plague Cloud";
            playerHealth -= PLAGUE_CLOUD_DAMAGE_PER_TURN;
            plagueCloudUsed = true;
        }

        if(spell.equals("Eruption")) {
            lastSpellUsed = "Eruption";
            playerHealth -= ERUPTION_DAMAGE;
        }
    }
}
