package Problem07.CreateZipArchive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CharsCounter {
    private static final String pathSource = "resources/Problem07/lines.txt";
    private static final String pathDest = "resources/Problem07/count-chars.txt";
    private static HashMap<Character, Integer> charCount;

    public static void main(String[] args) {
        charCount = new HashMap<>();
        countCharsInText();

    }

    private static void countCharsInText() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(pathSource));
                FileWriter writer = new FileWriter(pathDest)
        ) {
            String line;
            while((line = reader.readLine()) != null) {
                Integer length = line.length();
                for(int i = 0; i < length; i++) {
                    char character = line.charAt(i);
                    if(!charCount.containsKey(character)) {
                        charCount.put(character, 0);
                    }

                    charCount.put(character, charCount.get(character) + 1);
                }
            }

            for(Map.Entry<Character, Integer> entry : charCount.entrySet()) {
                writer.write(String.format("Character:%c  Count:%d%n", entry.getKey(), entry.getValue()));
            }
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
