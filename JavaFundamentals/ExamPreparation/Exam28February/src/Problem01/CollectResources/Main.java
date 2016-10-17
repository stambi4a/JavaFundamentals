package Problem01.CollectResources;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        collectResources();
    }

    private static void collectResources() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] resources = collectValidResources(input);
        int pathCount = Integer.parseInt(scanner.nextLine());
        int index = 1;
        int greatestResourceCollection = 0;
        while(index <= pathCount) {
            String[] pathParams = scanner.nextLine().split(" ");
            int start = Integer.parseInt(pathParams[0]);
            int step = Integer.parseInt(pathParams[1]);
            int pathCollection = calculatePath(start, step, resources);
            if(pathCollection > greatestResourceCollection) {
                greatestResourceCollection = pathCollection;
            }

            index++;
        }

        System.out.printf("%d", greatestResourceCollection);
    }
    private static int calculatePath(int start, int step, int[] resources) {
        int length = resources.length;
        boolean[] isCollected = new boolean[length];
        int resourcesCollection = 0;
        int index = start;
        while(true) {
            if(!isCollected[index]) {
                resourcesCollection += resources[index];
                isCollected[index] = true;
                index = (index + step) % length;
            } else {
                break;
            }
        }

        return resourcesCollection;
    }

    private static int[] collectValidResources(String input) {
        String[] inputParams = input.split(" ");
        int length = inputParams.length;
        int[] resources = new int[length];
        String resourcePattern = "([a-z]+)_*(\\d{0,3})";
        String resourceName = null;
        int resourceQuantity = 0;
        Pattern pattern = Pattern.compile(resourcePattern);
        for(int i = 0; i < length; i++) {
            Matcher match = pattern.matcher(inputParams[i]);
            if(match.find()) {
                resourceName = match.group(1);
                if(resourceName.equals("stone") ||
                        resourceName.equals("wood") ||
                        resourceName.equals("gold") ||
                        resourceName.equals("food")) {
                    String resourceNumber = match.group(2);
                    resourceQuantity = resourceNumber.length() > 0 ? Integer.parseInt(resourceNumber) : 1;
                    resources[i] = resourceQuantity;
                }
            }
        }

        return resources;
    }
}
