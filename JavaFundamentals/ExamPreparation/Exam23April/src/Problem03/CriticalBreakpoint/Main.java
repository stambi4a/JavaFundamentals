package Problem03.CriticalBreakpoint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long criticalRatio = 0;
        List<long[]> coordinates = new ArrayList<>();
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("Break it.")) {
                break;
            }

            long[] params = Arrays.asList(input.split("\\s")).stream().mapToLong(Long::parseLong).toArray();
            coordinates.add(params);
            long currentCriticalRatio = Math.abs(params[2] + params[3] - (params[0] + params[1]));
            if(criticalRatio != 0 && currentCriticalRatio != criticalRatio && currentCriticalRatio != 0) {
                System.out.println("Critical breakpoint does not exist.");
                return;
            }

            if(currentCriticalRatio != 0) {
                criticalRatio = currentCriticalRatio;
            }
        }

        int countLines = coordinates.size();
        BigDecimal bigCriticalRatio = BigDecimal.valueOf(criticalRatio);
        BigDecimal base = bigCriticalRatio.pow(countLines);
        BigDecimal criticalBreakpoint = base.remainder(BigDecimal.valueOf(countLines));
        for(int i = 0; i < countLines; i++) {
            System.out.println("Line: " + Arrays.toString(coordinates.get(i)));
        }

        System.out.println("Critical Breakpoint: " + criticalBreakpoint);

    }
}
