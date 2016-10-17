package Problem04.CopyJpgFile;

import java.io.*;
import java.io.IOException;

public class Main {
    private static final String pathSource = "resources/Problem04/ICantTakeItAnyMore.jpg";
    private static final String pathDest = "resources/Problem04/my-copied-picture.jpg";
    public static void main(String[] args) {
        copyJpgFile();
    }

    private static void copyJpgFile() {
        try (
                FileInputStream input = new FileInputStream(pathSource);
                FileOutputStream output = new FileOutputStream(pathDest)
        ) {
            byte[] buffer = new byte[4096];
            while(true) {
                int readResult = input.read(buffer);
                if(readResult == -1) {
                     break;
                }

                output.write(buffer);
            }
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}