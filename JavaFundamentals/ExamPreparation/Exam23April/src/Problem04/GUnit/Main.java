package Problem04.GUnit;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String unitPattern = "[A-Z][A-Za-z0-9]+";
        Pattern pattern = Pattern.compile(unitPattern);
        HashMap<String, HashMap<String, TreeSet<String>>> classes = new HashMap<>();
        HashMap<String, Integer> classesByUnitTestCount = new HashMap<>();
        HashMap<String, Integer> classesByMethodCount = new HashMap<>();
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("It's testing time!")) {
                break;
            }

            String[] params = input.split("\\s\\|\\s");
            if(params.length != 3 ) {
                continue;
            }

            int i;
            for(i = 0; i < 3; i++) {
                Matcher match = pattern.matcher(params[i]);
                if(!match.find()) {
                    break;
                }

                if(match.group().length() < params[i].length()) {
                    break;
                }
            }

            if(i < 3) {
                continue;
            }

            String className = params[0];
            String methodName = params[1];
            String unitTestName = params[2];
            classes.putIfAbsent(className, new HashMap<>());
            classesByMethodCount.putIfAbsent(className, 0);
            if(!classes.get(className).containsKey(methodName)) {
                classes.get(className).put(methodName, new TreeSet<>(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if(o1.length() == o2.length()) {
                            return o1.compareTo(o2);
                        }

                        return o1.length() - o2.length();
                    }
                }));
                int methodsCount = classesByMethodCount.get(className) + 1;
                classesByMethodCount.put(className, methodsCount);
            }


            if(!classes.get(className).get(methodName).contains(unitTestName)) {
                classes.get(className).get(methodName).add(unitTestName);
                classesByUnitTestCount.putIfAbsent(className, 0);
                int unitTestsCount = classesByUnitTestCount.get(className) + 1;
                classesByUnitTestCount.put(className, unitTestsCount);
            }

        }

        TreeMap<Integer, TreeMap<Integer, TreeMap<String, TreeMap<Integer, TreeSet<String>>>>> sortedClasses;
        sortedClasses = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
               return o2 - o1;
            }
        });

        for(String className : classesByMethodCount.keySet()) {
            int unitTestsCount = classesByUnitTestCount.get(className);
            int methodsCount = classesByMethodCount.get(className);
            sortedClasses.putIfAbsent(unitTestsCount, new TreeMap<>());
            sortedClasses.get(unitTestsCount).putIfAbsent(methodsCount, new TreeMap<>());
            sortedClasses.get(unitTestsCount).get(methodsCount).put(className, new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            }));
            for(String unsortedMethod : classes.get(className).keySet()) {
                int methodTestsCount = classes.get(className).get(unsortedMethod).size();
                sortedClasses.get(unitTestsCount).get(methodsCount).get(className).putIfAbsent(methodTestsCount, new TreeSet<>());
                sortedClasses.get(unitTestsCount).get(methodsCount).get(className).get(methodTestsCount).add(unsortedMethod);
            }
        }

        for(Integer unitTestsCount : sortedClasses.keySet()) {
            for(Integer methodsCount : sortedClasses.get(unitTestsCount).keySet()) {
                for(String className : sortedClasses.get(unitTestsCount).get(methodsCount).keySet()) {
                    System.out.println(className + ": ");
                    for(Integer testCount : sortedClasses.get(unitTestsCount).get(methodsCount).get(className).keySet()) {
                        for(String methodName : sortedClasses.get(unitTestsCount).get(methodsCount).get(className).get(testCount)) {
                            System.out.println("##" + methodName);
                            for(String testName : classes.get(className).get(methodName)) {
                                System.out.println("####" + testName);
                            }
                        }
                    }
                }
            }
        }
    }
}