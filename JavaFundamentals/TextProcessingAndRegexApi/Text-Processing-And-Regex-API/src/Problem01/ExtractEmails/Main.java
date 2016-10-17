package Problem01.ExtractEmails;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<String> emailList = extractEmails();
        printExtractedEmails(emailList);
    }

    private static void printExtractedEmails(List<String> emailList) {
        System.out.println(String.join("\n", emailList));
    }

    private static List<String> extractEmails() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String emailPattern = "([a-zA-Z0-9])(([.\\-_]?)[a-zA-Z0-9])+@([a-zA-Z][\\-.]?)+[a-zA-Z]";
        Pattern emailMatchPattern = Pattern.compile(emailPattern);
        Matcher matches = emailMatchPattern.matcher(text);
        List<String> emailList = new ArrayList<String>();
        while (matches.find()) {
            emailList.add(matches.group());
        }

        return emailList;
    }
}