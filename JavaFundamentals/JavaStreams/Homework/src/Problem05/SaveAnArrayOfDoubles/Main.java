package Problem05.SaveAnArrayOfDoubles;

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static final String pathSource = "resources/Problem05/doubles.list";

    private static ArrayList<Double> doublesList = new ArrayList<>();
    public static void main(String[] args) {
        doublesList.add(12.45);
        doublesList.add(24.90812745);
        doublesList.add(-0.0);
        doublesList.add(1000.123);
        doublesList.add(-2423723.293764);
        doublesList.add(-23.23847);
        doublesList.add(100.23974123123);
        doublesList.add(-101284701273.8923471283);
        doublesList.add(.56);
        doublesList.add(-0.891273);
        saveObject();
        loadObject();
    }

    private static void saveObject() {
        try (
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathSource))
        ) {
            output.writeObject(doublesList);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private static void loadObject() {
        try (
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathSource))
        ) {
            System.out.println("List of doubles:" + input.readObject());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}