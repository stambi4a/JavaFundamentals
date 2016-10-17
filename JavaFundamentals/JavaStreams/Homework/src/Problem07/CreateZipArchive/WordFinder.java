package Problem07.CreateZipArchive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WordFinder {
    private static final String pathSource = "resources/Problem07/lines.txt";
    private static final String pathDest = "resources/Problem07/words.txt";
    private static ArrayList<String> wordsInText;

    public static void main(String[] args) {
        wordsInText = new ArrayList<String>();
        FindWordsInText();

    }

    private static void FindWordsInText() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(pathSource));
                FileWriter writer = new FileWriter(pathDest)
        ) {
            String line;
            String delPattern = "\\s|\\.|,|\\?|!";
            while((line = reader.readLine()) != null) {

                String[] wordsInLine = line.split(delPattern);
                wordsInText.addAll(new ArrayList<>(Arrays.asList(wordsInLine)));
                }
            for(String word : wordsInText) {
                writer.write(word +"\n");
            }
            }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
