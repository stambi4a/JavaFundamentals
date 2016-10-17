package Problem02;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        scanner.nextLine();
        String[] prices = scanner.nextLine().split(" ");
        BigDecimal lukankaPrice = BigDecimal.valueOf(Double.parseDouble(prices[0]));
        BigDecimal rakijaPrice = BigDecimal.valueOf(Double.parseDouble(prices[1]));
        BigDecimal totalSum = BigDecimal.valueOf(0);
        int clients = 0;
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("Royal Close")) {
                break;
            }

            clients++;
            String[] params = input.split(" ");
            int row = Integer.parseInt(params[0]);
            int column = Integer.parseInt(params[1]);

            long lukankaCount = 0;
            long rakijaCount = 0;
            if(row >= column) {
                if(row % 2 == 0) {
                    for(int i = column; i > 0;i--) {
                        lukankaCount += (i + 1) * (row + 1);
                    }
                } else {
                    for(int i = column; i > 0; i--) {
                        rakijaCount += (i + 1) * (row + 1);
                    }
                }

                for(int j = row ; j > 0; j--) {
                    if(j % 2 == 0) {
                        lukankaCount += j + 1;
                    } else {
                        rakijaCount += j + 1;
                    }
                }
            } else {
                for(int j = row; j > 0; j--) {
                    if(j % 2 == 0) {
                        lukankaCount += (j + 1) * (column + 1);
                    } else {
                        rakijaCount += (j + 1) * (column + 1);
                    }
                }

                for(int i = column; i > 0;i--) {
                    lukankaCount += i + 1;
                }
            }

            totalSum = totalSum.add(BigDecimal.valueOf(rakijaCount).multiply(rakijaPrice));
            totalSum = totalSum.add(BigDecimal.valueOf(lukankaCount).multiply(lukankaPrice));
        }

        System.out.printf("%.6f%n", totalSum);
        System.out.println(clients);
    }
}
