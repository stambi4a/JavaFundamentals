package Problem02.AllCApitals;

import java.io.*;

public class Main {
    private static final String pathSource = "resources/Problem02/Problem02";
    private static final String pathDest = "resources/Problem02/output";
    public static void main(String[] args) {
        convertTextToUpperCase();
    }

    private static void convertTextToUpperCase() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(pathSource));
                PrintWriter printer = new PrintWriter(new FileWriter(pathDest))
        ) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                line = line.toUpperCase();
                printer.write(line + "\n");
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        try (
                BufferedReader reader = new BufferedReader(new FileReader(pathDest));
                PrintWriter printer = new PrintWriter(new FileWriter(pathSource))
        ) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                line = line.toUpperCase();
                printer.write(line + "\n");
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        File file = new File(pathDest);
        file.delete();
    }
}
