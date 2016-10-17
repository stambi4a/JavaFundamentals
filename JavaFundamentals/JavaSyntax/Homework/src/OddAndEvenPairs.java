import java.util.Scanner;

public class OddAndEvenPairs {
    public static void main(String[] args) {
        try {
            Integer[] numbers = inputArray();
            checkPairs(numbers);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }

    private static void checkPairs(Integer[] numbers) {
        int length = numbers.length;
        for(int i = 0;i < length - 1; i = i + 2) {
            if((numbers[i] * numbers[i + 1]) % 2 == 0 && (numbers[i] + numbers[i + 1]) % 2 == 0) {
                System.out.printf("%1$d, %2$d -> both are even", numbers[i], numbers[i + 1]);
            }

            if((numbers[i] * numbers[i + 1]) % 2 == 1) {
                System.out.printf("%1$d, %2$d -> both are odd", numbers[i], numbers[i + 1]);
            }

            if((numbers[i] * numbers[i + 1]) % 2 == 0 && (numbers[i] + numbers[i + 1]) % 2 == 1) {
                System.out.printf("%1$d, %2$d -> different", numbers[i], numbers[i + 1]);
            }
            System.out.println();
        }
    }

    private static Integer[] inputArray()
    {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int length = input.length;
        if(length % 2 == 1) {
            throw new IllegalArgumentException("Invalid length");
        }

        Integer[] numbers = new Integer[length];
        for(int i = 0; i < length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        return numbers;
    }
}
