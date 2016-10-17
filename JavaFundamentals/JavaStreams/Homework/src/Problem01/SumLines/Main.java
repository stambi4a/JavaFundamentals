package Problem01.SumLines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static final String pathSource = "resources/Problem01/Problem01";

    public static void main(String[] args) {
        sumLinesCharacters();
    }

    private static void sumLinesCharacters() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader (pathSource))
        ) {
            while(true) {
                Integer sumLines = 0;
                String line = reader.readLine();
                if(line == null)
                {
                    break;
                }

                Integer length = line.length();
                for(Integer i = 0; i < length; i++) {
                    sumLines += line.charAt(i);
                }

                System.out.println(sumLines);
            }
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}