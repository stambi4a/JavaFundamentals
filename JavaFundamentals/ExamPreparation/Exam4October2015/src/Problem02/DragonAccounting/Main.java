package Problem02.DragonAccounting;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final int MONTH_DAYS = 30;
    private static final int YEAR_DAYS = 365;
    public static void main(String[] args) {
        manageDragonAccounting();
    }

    private static void manageDragonAccounting() {
        HashMap<Integer, Long> hiredWorkers = new HashMap<>();
        HashMap<Integer, BigDecimal> workersSalaries = new HashMap<>();
        /*HashMap<Integer, Double> workersIncrease = new HashMap<>();*/
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        BigDecimal capital = new BigDecimal(value);
        int days = 1;
        boolean isBankrupt = false;
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("END")) {
                break;
            }

            capital = manageDayAccounting(hiredWorkers, workersSalaries, days, capital, input);
            isBankrupt = checkForBankruptcy(capital);
            if(isBankrupt) {
                break;
            }
            days++;
        }

        days--;
        printResult(isBankrupt, capital, hiredWorkers, days);
    }

    private static Long calculateRemainingWorkers(HashMap<Integer, Long> hiredWorkers, int days) {
        Long remainingWorkers = 0L;
        for(int i = 1; i <= days; i++) {
            remainingWorkers += hiredWorkers.get(i);
        }

        return remainingWorkers;
    }

    private static void printResult(boolean isBankrupt,
                                    BigDecimal capital,
                                    HashMap<Integer, Long> hiredWorkers,
                                    int days) {
        capital = capital.setScale(4, BigDecimal.ROUND_DOWN);
        if(isBankrupt) {
            System.out.println("BANKRUPTCY: "+ capital.negate());
        } else {
            Long remainingWorkers = calculateRemainingWorkers(hiredWorkers, days);
            System.out.println(remainingWorkers + " " + capital);
        }
    }

    private static BigDecimal manageDayAccounting(
            HashMap<Integer, Long> hiredWorkers,
            HashMap<Integer, BigDecimal> workersSalaries,
            int days,
            BigDecimal capital,
            String input) {
        String[] params = input.split(";");
        hireWorkers(hiredWorkers, workersSalaries, days, params[0], params[2]);
        if(days >= YEAR_DAYS) {
            checkForRaise(hiredWorkers, workersSalaries, days);
        }

        if(days %30 == 0) {
            capital = paySalaries(hiredWorkers, workersSalaries, days ,capital);
        }

        fireWorkers(hiredWorkers, days, params[1]);
        capital = calculateIncomeExpense(capital, params);

        return capital;
    }

    private static boolean checkForBankruptcy(BigDecimal capital) {
        if(capital.compareTo(BigDecimal.ZERO) < 0) {
            return true;
        }

        return false;
    }

    private static BigDecimal calculateIncomeExpense(BigDecimal capital, String [] params) {
        int length = params.length;
        for(int i = 3; i < length ;i++) {
            String[] eventParams = params[i].split(":");
            String eventName = eventParams[0];
            BigDecimal eventMoney = new BigDecimal(eventParams[1]);
            switch(eventName) {
                case "Machines" :
                case "Taxes" :
                case "Previous years deficit" :
                {
                    eventMoney = eventMoney.negate();
                }

                break;
                default : break;
            }
            capital = capital.add(eventMoney);
        }

        return capital;
    }

    private static void fireWorkers(HashMap<Integer, Long> hiredWorkers, int days, String paramFired) {
        Long fired = Long.parseLong(paramFired);
        int indexDays = 1;
        while(fired > 0 && indexDays <= days) {
            Long hiredOnThatDayLeft = hiredWorkers.get(indexDays);

            if(fired >= hiredOnThatDayLeft) {
                fired -= hiredOnThatDayLeft;
                hiredOnThatDayLeft = 0L;
            } else {
                hiredOnThatDayLeft -= fired;
                fired = 0L;
            }

            hiredWorkers.put(indexDays, hiredOnThatDayLeft);
            indexDays++;
        }
    }

    private static BigDecimal paySalaries(
            HashMap<Integer, Long> hiredWorkers,
            HashMap<Integer, BigDecimal> workersSalaries,
            int days,
            BigDecimal capital) {
        int indexDays = 1;
        while(true && indexDays <= days) {
            Long hired = hiredWorkers.get(indexDays);
            if(hired != 0) {
                break;
            }

            indexDays++;
        }



        while(indexDays <= days) {
            Long hired = hiredWorkers.get(indexDays);
            int daysToPay = days - indexDays >= MONTH_DAYS ? MONTH_DAYS : (days - indexDays + 1)%(MONTH_DAYS + 1);
            BigDecimal paidSalary = workersSalaries.get(indexDays).
                    multiply(BigDecimal.valueOf(hired)).
                    multiply(BigDecimal.valueOf(daysToPay));
            capital = capital.subtract(paidSalary);

            indexDays++;
        }

        return capital;
    }

    private static void checkForRaise(
            HashMap<Integer, Long> hiredWorkers,
            HashMap<Integer, BigDecimal> workersSalaries,
            int days) {
        int index = 1;
        int daysToCheck = days + 1 - YEAR_DAYS * index;
        while(daysToCheck > 0) {
            Long hiredOnThatDayLeft = hiredWorkers.get(daysToCheck);
            if(hiredOnThatDayLeft > 0) {
                BigDecimal salary = workersSalaries.get(daysToCheck);
               /* int digits = salary.intValue();
                if(digits > 0) {
                    digits = (int)Math.log(digits) + 1;
                }
                MathContext mc = new MathContext(digits + 9, RoundingMode.DOWN);*/
                BigDecimal oldSalary = salary.setScale(9, BigDecimal.ROUND_UP);
                salary = salary.multiply(BigDecimal.valueOf(6)).divide(BigDecimal.valueOf(1000));
                salary = salary.add(oldSalary);

                salary = salary.setScale(7, BigDecimal.ROUND_DOWN);
                workersSalaries.put(daysToCheck, salary);
            }

            index++;
            daysToCheck = days + 1 - YEAR_DAYS * index;
        }

    }

    private static void hireWorkers(
            HashMap<Integer, Long> hiredWorkers,
            HashMap<Integer, BigDecimal> workersSalaries,
            int days,
            String paramHiredNumber,
            String paramSalaryHired) {
        Long hired = Long.parseLong(paramHiredNumber);
        BigDecimal salary = new BigDecimal(paramSalaryHired);
        salary = salary.divide(BigDecimal.valueOf(MONTH_DAYS),9, BigDecimal.ROUND_UP);
        salary = salary.setScale(7, BigDecimal.ROUND_DOWN);
        hiredWorkers.put(days, hired);
        workersSalaries.put(days, salary);
    }
}
