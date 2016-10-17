import java.util.Scanner;

public class HitTheTarget {
    private static final int StartNumber = 1;
    private static final int EndNumber = 20;
    public static void main(String[] args) {
        hitTheTarget();
    }

    private static void hitTheTarget()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Integer target = scanner.nextInt();
            if(target > 2 * EndNumber || target < -StartNumber + 1) {
                throw new IllegalArgumentException("Invalid target");
            }

            findAllSumsEqualTarget(target);
            findAllSubtractionsEqualTarget(target);
        } catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }

    private static void findAllSumsEqualTarget(Integer target) {
        if(target <= EndNumber) {
            for(int i = 1; i < target; i++) {
                System.out.printf("%d + %d = %d%n" +
                        "", i , target - i, target);
            }
        } else {
            for(int i = target - EndNumber; i <= EndNumber; i++) {
                System.out.printf("%d + %d = %d%n" +
                        "", i , target - i, target);
            }
        }
    }

    private static void findAllSubtractionsEqualTarget(Integer target) {
        for(int i = target + 1; i <= EndNumber; i ++) {
            System.out.printf("%d - %d = %d%n" +
                    "", i , i - target, target);
        }
    }
}
