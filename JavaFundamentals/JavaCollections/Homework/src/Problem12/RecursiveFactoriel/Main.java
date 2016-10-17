package Problem12.RecursiveFactoriel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        long fact = 1;
        if(number > 1) {
            fact = recursivelyCalculateFactorial(number, 1, 1);
        }

        System.out.println(fact);
    }

    private static long recursivelyCalculateFactorial(int number, int index, long fact) {
        if(index == number) {
            return index;
        }

        fact = index * recursivelyCalculateFactorial(number,index+1, fact);

        return fact;
    }
}