import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Box> boxes = new ArrayList<>();

        try {
            int lines = Integer.valueOf(reader.readLine());
            for (int i = 0; i < lines; i++) {
                Box box = new Box(reader.readLine());
                boxes.add(box);
            }

            Box boxToCompare = new Box(reader.readLine());
            System.out.println(countGreater(boxes, boxToCompare));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <E extends Comparable<E>> int countGreater(List<E> list, E element) {
        int counter = 0;
        for (E box : list) {
            if (box.compareTo(element)==1){
                counter++;
            }
        }
        return counter;
    }
}
