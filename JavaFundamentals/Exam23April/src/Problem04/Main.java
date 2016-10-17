package Problem04;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, HashMap<String, HashSet<String>>> classesWithMethodsAndTests = new HashMap<>();
        HashMap<String, Integer> classesWithTests = new HashMap<>();
        HashMap<String, Integer> classesWithMethods = new HashMap<>();
        while(true) {
            String line = scanner.nextLine();
            if(line.equals("It's testing time!")) {
                break;
            }

            String[] params = line.split(" | ");
            if(params.length == 3) {
                Pattern pattern = Pattern.compile("\\b[A-Z]([A-Za-z0-9]+)\\b");
                Matcher match = pattern.matcher(line);
                int index = 0;
                while(match.find()) {
                    index++;
                }
                if(index == 3) {
                    String className = params[0];
                    String method = params[1];
                    String test = params[2];
                    classesWithMethodsAndTests.putIfAbsent(className, new HashMap<>());
                    classesWithMethodsAndTests.get(className).putIfAbsent(method, new HashSet<>());
                    classesWithMethods.putIfAbsent(className, 0);
                    if(!classesWithMethodsAndTests.get(className).containsValue(method)) {
                        int methodCount = classesWithMethods.get(className);
                        classesWithMethods.put(className, methodCount + 1);
                    }

                    if(!classesWithMethodsAndTests.get(className).get(method).contains(test)) {
                        classesWithMethodsAndTests.get(className).get(method).add(test);
                        classesWithTests.putIfAbsent(className, 0);
                        int testCount = classesWithTests.get(className);
                        classesWithTests.put(className, testCount + 1);
                    }

                    classesWithMethods.putIfAbsent(className, 0);

                }
            }
        }

        TreeMap<Integer, HashSet<String>> orderedByTests = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for(String className : classesWithTests.keySet()) {
            int testCount = classesWithTests.get(className);
            orderedByTests.putIfAbsent(classesWithTests.get(className), new HashSet<>());
            if(!orderedByTests.get(testCount).contains(className)) {
                orderedByTests.get(testCount).add(className);
            }
        }

        TreeMap<Integer, TreeSet<String>> orderedByMethods = new TreeMap<>();

        for(String className : classesWithMethods.keySet()) {
            int methodCount = classesWithMethods.get(className);
            orderedByMethods.putIfAbsent(classesWithMethods.get(className), new TreeSet<>());
            if(!orderedByMethods.get(methodCount).contains(className)) {
                orderedByMethods.get(methodCount).add(className);
            }
        }



    }
}
