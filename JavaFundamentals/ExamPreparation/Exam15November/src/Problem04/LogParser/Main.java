package Problem04.LogParser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        registerErrors();
    }

    private static void registerErrors () {
        HashMap<String, List<String>> criticalErrors = new HashMap<>();
        HashMap<String, List<String>> warningErrors = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("END")) {
                break;
            }
           /* String changedInput = input.replaceAll(" ", "");
            String[] params = changedInput.split(",:");*/
            /*String[] params = input.split("(,\\s)|(:\\s)");*/
            String params[] = parseInput(input);
            registerError(params, criticalErrors, warningErrors);
        }

        TreeMap<Integer, List<String>> projectsByTotalErrors = new TreeMap<>(new Main().new IntegerListComparator());
        sortProjectByErrors(criticalErrors, warningErrors, projectsByTotalErrors);
        printProjectsByErrors(criticalErrors, warningErrors, projectsByTotalErrors);
    }

    private static String[] parseInput(String input) {
        String params[] = new String[3];
        String paramsPattern = "(\\[\")(.+?)(\"\\])";
        Pattern pattern = Pattern.compile(paramsPattern);
        Matcher match = pattern.matcher(input);
        int index = 0;
        while(match.find()) {
            params[index] = match.group(2);
            index++;
        }

        return params;
    }

    private static void printProjectsByErrors(
            HashMap<String, List<String>> criticalErrors,
            HashMap<String, List<String>> warningErrors,
            TreeMap<Integer, List<String>> projectsByTotalErrors) {
        Set<Integer> totalErrorsList = projectsByTotalErrors.keySet();
        int size = totalErrorsList.size();
        int index = 1;
        for(Integer totalErrors : totalErrorsList) {
            List<String> projects = projectsByTotalErrors.get(totalErrors);
            int sizeProjects = projects.size();
            int indexProjects = 1;
            Collections.sort(projects);
            for(String project : projects) {
                System.out.println(project + ":");
                System.out.println("Total Errors: " + totalErrors);

                int criticalErrorsCount = 0;
                List<String> projectCriticalErrors = null;
                if(criticalErrors.containsKey(project)) {
                    projectCriticalErrors = criticalErrors.get(project);
                    criticalErrorsCount = projectCriticalErrors.size();
                }

                System.out.println("Critical: " + criticalErrorsCount);

                int warningErrorsCount = 0;
                List<String> projectWarningErrors = null;
                if(warningErrors.containsKey(project)) {
                    projectWarningErrors = warningErrors.get(project);
                    warningErrorsCount = projectWarningErrors.size();
                }

                System.out.println("Warnings: " + warningErrorsCount);

                System.out.println("Critical Messages:");
                if(criticalErrorsCount > 0) {
                    Collections.sort(projectCriticalErrors, new Main().new ListComparator());
                    for(String message : projectCriticalErrors) {
                        System.out.println("--->" + message);
                    }
                } else {
                    System.out.println("--->None");
                }

                System.out.println("Warning Messages:");
                if(warningErrorsCount > 0) {
                    Collections.sort(projectWarningErrors, new Main().new ListComparator());
                    for(String message : projectWarningErrors) {
                        System.out.println("--->" + message);
                    }
                } else {
                    System.out.println("--->None");
                }

                if(indexProjects < sizeProjects || index < size) {
                    System.out.println();
                }
                indexProjects++;
            }

            index++;
        }
    }

    private static void sortProjectByErrors(
            HashMap<String, List<String>> criticalErrors,
            HashMap<String, List<String>> warningErrors,
            TreeMap<Integer, List<String>> projectsByTotalErrors) {
        Set<String> projects = criticalErrors.keySet();
        for(String project : projects) {
            int totalErrors = criticalErrors.get(project).size();
            if(warningErrors.containsKey(project)) {
                totalErrors += warningErrors.get(project).size();
            }

            projectsByTotalErrors.putIfAbsent(totalErrors, new ArrayList<>());
            projectsByTotalErrors.get(totalErrors).add(project);
        }

        projects = warningErrors.keySet();
        for(String project : projects) {
            if(!criticalErrors.containsKey(project)) {
                int totalErrors = warningErrors.get(project).size();
                projectsByTotalErrors.putIfAbsent(totalErrors, new ArrayList<>());
                projectsByTotalErrors.get(totalErrors).add(project);
            }
        }
    }

    private static void registerError(
            String[] params,
            HashMap<String, List<String>> criticalErrors,
            HashMap<String, List<String>> warningErrors) {
        String projectName = params[0];
        String errorType = params[1];
        String errorMessage = params[2];
        if(errorType.equals("Critical")) {
            criticalErrors.putIfAbsent(projectName, new ArrayList<>());
            criticalErrors.get(projectName).add(errorMessage);
        }
        if(errorType.equals("Warning")){
            warningErrors.putIfAbsent(projectName, new ArrayList<>());
            warningErrors.get(projectName).add(errorMessage);
        }
    }

    public class IntegerListComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public class ListComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int comparison = o1.length() - o2.length();
            if (comparison != 0) {
                return comparison;
            }

            return o1.compareTo(o2);
        }
    }
}