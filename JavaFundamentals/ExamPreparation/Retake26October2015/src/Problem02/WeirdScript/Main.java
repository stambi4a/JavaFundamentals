package Problem02.WeirdScript;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int ALPHABET_LENGTH = 26;

    public static void main(String[] args) {
        extractText();
    }

    private static void extractText() {
        Scanner scanner = new Scanner(System.in);
        int codeNumber = Integer.parseInt(scanner.nextLine());
        char codeLetter = createKeyLetter(codeNumber);
        String key = Character.toString(codeLetter);
        key = key + key;
        StringBuilder text = new StringBuilder();
        while(true) {
            String line = scanner.nextLine();
            if(line.equals("End")){
                break;
            }

            text.append(line);
        }

        String textToSearch = text.toString();

        String extractedText = extractTextWithCodeLetter(textToSearch, key);
        System.out.printf(extractedText);
    }

    private static String extractTextWithCodeLetter(String textToSearch, String key) {
        List<String> text = new ArrayList();
        int startIndex = 0;
        while(true) {
            startIndex = textToSearch.indexOf(key,startIndex);
            if(startIndex == -1) {
                break;
            }
            startIndex += 2;
            int firstIndex = startIndex;
            startIndex = textToSearch.indexOf(key,startIndex);
            if(startIndex == -1) {
                break;
            }

            int lastIndex = startIndex;
            startIndex += 2;
            String foundText = textToSearch.substring(firstIndex, lastIndex);
            text.add(foundText);
        }

        return String.join("\n", text);
    }

    private static char createKeyLetter(int codeNumber) {
        int remainder = ((codeNumber - 1)/ ALPHABET_LENGTH) % 2;
        char codeLetter = 'o';
        if(remainder == 0) {
            codeLetter = (char)((codeNumber - 1) % ALPHABET_LENGTH  + 'a');
        }

        if(remainder == 1) {
            codeLetter = (char)((codeNumber - 1) % ALPHABET_LENGTH  + 'A');
        }

        return codeLetter;
    }

}
