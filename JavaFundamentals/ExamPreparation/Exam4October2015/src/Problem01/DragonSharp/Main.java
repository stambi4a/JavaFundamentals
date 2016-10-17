package Problem01.DragonSharp;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String COMPILE_TIME_ERROR_MESSAGE = "Compile time error @ line %d";
    public static void main(String[] args) {
        writeDragonSharp();
    }

    private static void writeDragonSharp() {
        Scanner scanner = new Scanner(System.in);
        int countLines = Integer.parseInt(scanner.nextLine());
        int index = 1;
        boolean isElseStatement = false;
        List<String> messages = new ArrayList<>();
        while(index <= countLines) {
            try{
                String input = scanner.nextLine();
                isElseStatement = compileDragonSharpCommand(input, index, isElseStatement, messages);
                index++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        System.out.println(String.join("\n", messages));
    }

    private static boolean compileDragonSharpCommand(String input, int index, boolean isElseStatement, List<String> messages) throws Exception {
        int length = input.length();
        if(input.charAt(length - 1) != ';') {
            throw new Exception(String.format(COMPILE_TIME_ERROR_MESSAGE, index));
        }

        int firstIndex = input.indexOf('\"');
        if(firstIndex == -1) {
            throw new Exception(String.format(COMPILE_TIME_ERROR_MESSAGE, index));
        }

        int lastIndex = input.lastIndexOf('\"');
        if(lastIndex == -1 || lastIndex == firstIndex) {
            throw new Exception(String.format(COMPILE_TIME_ERROR_MESSAGE, index));
        }

        String statement = input.substring(0, firstIndex - 1);
        String printText = input.substring(firstIndex + 1, lastIndex);

        String[] paramsFirst = statement.split(" ");
        String[] params = new String[paramsFirst.length + 1];
        for(int i = 0; i < params.length - 1; i++) {
            params[i] = paramsFirst[i];
        }

        params[params.length - 1] = printText;

        if(!isElseStatement && params[0].equals("else")) {
            return false;
        }

        String codePattern = "(\\d+)([<>]|==)(\\d+)";
        Pattern pattern = Pattern.compile(codePattern);
        Matcher match = pattern.matcher(params[1]);

        if(params.length == 4 || params.length == 6) {
            if (match.find()) {
                int leftArgument = Integer.parseInt(match.group(1));
                int rightArgument = Integer.parseInt(match.group(3));
                String operator = match.group(2);
                if ((operator.equals("<") && leftArgument < rightArgument) ||
                        (operator.equals(">") && leftArgument > rightArgument) ||
                        (operator.equals("==") && leftArgument == rightArgument)) {
                } else {
                    return true;
                }
            }
        }

        String stringToPrint = null;
        if(params.length == 4) {
            stringToPrint = params[3];
        }

        if(params.length == 6) {
            stringToPrint = params[5];
        }

        if(params.length == 5) {
            stringToPrint = params[4];
        }

        if(params.length == 3) {
            stringToPrint = params[2];
        }

        List<String> text = new ArrayList<>();
        int repeatCount = 1;
        String param = null;
        if(params.length == 6) {
            param = params[3];
        }

        if(params.length == 5) {
            param = params[2];
        }

        if(param != null) {
            repeatCount = Integer.parseInt(param);
        }


        if(repeatCount == 0) {
            return false;
        }

        for(int i = 1;i <= repeatCount; i++) {
            text.add(stringToPrint);
        }

        String textToPrint = String.join("\n", text);
        messages.add(textToPrint);

        return false;
    }
}
