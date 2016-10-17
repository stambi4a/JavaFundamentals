package Problem04;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, HashSet<String>> employees = new HashMap<>();
        HashMap<String, Integer> employeesWithWorkHours = new HashMap<>();
        HashMap<String, BigDecimal> employeesWithDailyPayment = new HashMap<>();
        int workHoursPerDay = 0;
        BigDecimal dailyPayment = BigDecimal.valueOf(0.0d);
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("Pishi kuf i da si hodim")) {
                break;
            }

            String[] params = input.split(";");

            try {
                workHoursPerDay = Integer.parseInt(params[1]);
                dailyPayment = BigDecimal.valueOf(Double.parseDouble(params[2]));

            } catch (NumberFormatException  nfe) {
                continue;
            }

            String namePattern = "[a-zA-Z]+";
            Pattern pattern = Pattern.compile(namePattern);
            Matcher match = pattern.matcher(params[0]);
            if(match.find()) {
                if(match.group().length() < params[0].length()) {
                    continue;
                }
            }

            match = pattern.matcher(params[3]);
            if(match.find()) {
                if (match.group().length() < params[3].length()) {
                    continue;
                }
            }

            String employee = params[0];
            String team = params[3];
            employees.putIfAbsent(team, new HashSet<>());
            if (employees.get(team).contains(employee)) {
                continue;
            }

            employees.get(team).add(employee);
            employees.get(team).add(employee);
            employeesWithDailyPayment.put(employee, dailyPayment);
            employeesWithWorkHours.put(employee, workHoursPerDay);
        }

        TreeMap<BigDecimal, HashSet<String>> teamsByMonthlyIncome = new TreeMap<>(new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal o1, BigDecimal o2) {
               return o2.compareTo(o1);
            }
        });

        HashMap<String, TreeMap<Integer, TreeMap<BigDecimal, TreeSet<String>>>> employeesByWorkhoursAndIncome
        = new HashMap<>();

        for(String team : employees.keySet()) {
            BigDecimal teamIncome = BigDecimal.valueOf(0);
            employeesByWorkhoursAndIncome.put(team, new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            }));
            for(String emp : employees.get(team)) {
                workHoursPerDay = employeesWithWorkHours.get(emp);
                dailyPayment = employeesWithDailyPayment.get(emp);
                BigDecimal dailyIncome = BigDecimal.valueOf(workHoursPerDay).multiply(dailyPayment).divide(BigDecimal.valueOf(24), 7,
                        BigDecimal.ROUND_DOWN);
                BigDecimal monthlyIncome = dailyIncome.multiply(BigDecimal.valueOf(30));
                teamIncome = teamIncome.add(monthlyIncome);
                employeesByWorkhoursAndIncome.get(team).putIfAbsent(workHoursPerDay, new TreeMap<>(new Comparator<BigDecimal>() {
                    @Override
                    public int compare(BigDecimal o1, BigDecimal o2) {
                        return o2.compareTo(o1);
                    }
                }));

                employeesByWorkhoursAndIncome.get(team).get(workHoursPerDay).putIfAbsent(dailyIncome, new TreeSet<>());
                employeesByWorkhoursAndIncome.get(team).get(workHoursPerDay).get(dailyIncome).add(emp);

            }

            teamsByMonthlyIncome.putIfAbsent(teamIncome, new HashSet<>());
            teamsByMonthlyIncome.get(teamIncome).add(team);
        }

        printAccounting(employeesByWorkhoursAndIncome, teamsByMonthlyIncome);

    }

    private static void printAccounting(
            HashMap<String, TreeMap<Integer, TreeMap<BigDecimal, TreeSet<String>>>> employeesByWorkhoursAndIncome,
            TreeMap<BigDecimal, HashSet<String>> teamsByMonthlyIncome) {
        for(Map.Entry<BigDecimal, HashSet<String>> teamWithIncome : teamsByMonthlyIncome.entrySet()) {
            for(String team : teamWithIncome.getValue()) {
                System.out.println("Team - " + team);
                for(Integer workHours : employeesByWorkhoursAndIncome.get(team).keySet()) {
                    for(BigDecimal dailyPayment : employeesByWorkhoursAndIncome.get(team).get(workHours).keySet()) {
                        for(String employee : employeesByWorkhoursAndIncome.get(team).get(workHours).get(dailyPayment)) {
                            System.out.printf("$$$%1$s - %2$d - %3$.6f%n", employee, workHours, dailyPayment);
                        }
                    }
                }
            }
        }
    }
}
