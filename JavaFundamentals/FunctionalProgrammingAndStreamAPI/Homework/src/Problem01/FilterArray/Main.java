package Problem01.FilterArray;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Collection<String> strings = filterArrayOfStrings();
        printStringCollection(strings);
    }

    private static void printStringCollection(Collection<String> strings) {
        System.out.println(strings.toString().replaceAll("\\[|\\]|,", ""));
    }

    private static Collection<String>  filterArrayOfStrings() {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(" ");
        List<String> longerStrings = Arrays.asList(strings).stream().filter(x->x.length() > 3).collect(Collectors.toList());

        return longerStrings;
    }
}