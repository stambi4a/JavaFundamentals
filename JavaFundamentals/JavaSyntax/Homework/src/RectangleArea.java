import java.util.Scanner;

public class RectangleArea {
    public static void main(String[] args) {
        calculateRectangleArea();
    }

    public static void calculateRectangleArea()
    {
        Scanner scanner = new Scanner(System.in);
        int width = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        Long area = (long)width * height;
        System.out.println(area);
    }
}
