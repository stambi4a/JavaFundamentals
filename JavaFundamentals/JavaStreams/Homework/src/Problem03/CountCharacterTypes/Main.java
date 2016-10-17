package Problem03.CountCharacterTypes;

import java.io.*;

public class Main {

    private static final String pathSource = "resources/Problem03/words";

    private static String vowels = "aoeiu";
    private static String consonants = "bcdfghjklmnpqrstvwxyz";
    private static String punctuationMarks = ".,?!";
    public static void main(String[] args) {
        countCharacterTypes();
    }

    private static void countCharacterTypes() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(pathSource))
                ) {
                    int vowelsCount = 0;
                    int consonantsCount = 0;
                    int punctuationMarksCount = 0;

                    while(true)
                    {
                        String line = reader.readLine();
                        if(line == null)
                        {
                            break;
                        }
                        int length = line.length();
                        line = line.toLowerCase();
                        for(int i = 0; i < length; i++) {
                            String character = Character.toString(line.charAt(i));
                            if(vowels.indexOf(character) != -1)
                            {
                                vowelsCount++;
                            }

                            if(consonants.indexOf(character) != -1)
                            {
                                consonantsCount++;
                            }

                            if(punctuationMarks.indexOf(character) != -1)
                            {
                                punctuationMarksCount++;
                            }
                        }

                        System.out.printf("%s: %d%n", "Vowels", vowelsCount);
                        System.out.printf("%s: %d%n", "Consonants", consonantsCount);
                        System.out.printf("%s: %d%n", "Punctuation Marks", punctuationMarksCount);
                    }
                }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}