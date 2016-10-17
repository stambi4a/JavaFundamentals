package Problem07.CreateZipArchive;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    private static final String pathSourceLines = "resources/Problem07/lines.txt";
    private static final String pathWords = "resources/Problem07/words.txt";
    private static final String pathCharCounts = "resources/Problem07/count-chars.txt";

    private static final String pathDest = "resources/Problem07/text-files.zip";

    public static void main(String[] args) {
        zipFiles();
    }

    private static void zipFiles() {
        try  {
            ZipOutputStream zipper = new ZipOutputStream(new FileOutputStream(pathDest));
            ZipEntry zipEntry = new ZipEntry(pathSourceLines);
            zipper.putNextEntry(zipEntry);
            FileInputStream reader = new FileInputStream(pathSourceLines);
            byte[] buffer = new byte[4096];
            while(true) {
                int readResult = reader.read(buffer);
                if (readResult == -1) {
                    break;
                }

                zipper.write(buffer, 0, readResult);
            }

            reader.close();
            zipEntry = new ZipEntry(pathCharCounts);
            zipper.putNextEntry(zipEntry);
            reader = new FileInputStream(pathCharCounts);
            while(true) {
                int readResult = reader.read(buffer);
                if (readResult == -1) {
                    break;
                }

                zipper.write(buffer, 0, readResult);
            }

            reader.close();
            zipEntry = new ZipEntry(pathWords);
            zipper.putNextEntry(zipEntry);
            reader = new FileInputStream(pathWords);
            while(true) {
                int readResult = reader.read(buffer);
                if (readResult == -1) {
                    break;
                }

                zipper.write(buffer, 0, readResult);
            }

            reader.close();
            zipper.closeEntry();
            zipper.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
    }
}