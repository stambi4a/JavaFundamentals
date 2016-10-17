package Problem03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int criticalRatio = 0;
        List<String> input = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("Break it.")) {
                break;
            }

            input.add(line);
        }
        int index = 0;
        int[][] matrix = new int[input.size()][4];
        while(index < input.size()) {

            String[] params =  input.get(index).split(" ");
            int firstX = Integer.parseInt(params[0]);
            int firstY = Integer.parseInt(params[1]);
            int secondX = Integer.parseInt(params[2]);
            int secondY = Integer.parseInt(params[3]);
            matrix[index][0] = firstX;
            matrix[index][1] = firstY;
            matrix[index][2] = secondX;
            matrix[index][3] = secondY;

            int valueRatio = Math.abs(secondX + secondY - firstX - firstY);
            if(criticalRatio == 0) {
                criticalRatio = valueRatio;
            }

            if(valueRatio != criticalRatio && valueRatio != 0) {
                System.out.println("Critical breakpoint does not exist.");
                return;
            }

            index++;
        }

        long criticalBreakpoint = 0;
        if(criticalRatio > 0) {
            criticalBreakpoint = ((int)Math.pow(criticalRatio, index)) % index;
        }

        printMatrix(matrix);
        System.out.println("Critical Breakpoint: " + criticalBreakpoint);

    }

    private static void printMatrix(int[][]matrix) {
        int rows = matrix.length;
        for(int i = 0; i < rows; i++) {
            System.out.println("Line: " + Arrays.toString(matrix[i]));
        }
    }
}
