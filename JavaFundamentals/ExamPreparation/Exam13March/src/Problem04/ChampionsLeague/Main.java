package Problem04.ChampionsLeague;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        playChampionsLeague();
    }

    private static void playChampionsLeague() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Set<String>> teamsWithOpponents = new HashMap<>();
        HashMap<String, Integer> teamsWithWins = new HashMap<>();
        while(true) {
            String line = scanner.nextLine();
            if(line.equals("stop")) {
                break;
            }

            processResult(line, teamsWithOpponents, teamsWithWins);
        }

        TreeMap<Integer, TreeSet<String>> teamsByWins = sortTeamsByWins(teamsWithWins);
        printTeamsByWins(teamsByWins, teamsWithOpponents);
    }

    private static void printTeamsByWins(TreeMap<Integer, TreeSet<String>> teamsByWins, HashMap<String, Set<String>> teamsWithOpponents) {
        for(Map.Entry<Integer, TreeSet<String>> entry : teamsByWins.entrySet()) {
            int wins = -entry.getKey();
            for(String team: entry.getValue()) {
                System.out.printf("%s%n", team);
                System.out.printf("- Wins: %d%n", wins);
                System.out.print("- Opponents: ");
                String opponents = String.join(", ", teamsWithOpponents.get(team));
                System.out.println(opponents);
            }
        }
    }

    private static TreeMap<Integer, TreeSet<String>> sortTeamsByWins(HashMap<String, Integer> teamsWithWins) {
        TreeMap<Integer, TreeSet<String>> teamsByWins = new TreeMap<>();
        for(Map.Entry<String, Integer> entry : teamsWithWins.entrySet()) {
            int wins = entry.getValue();
            teamsByWins.putIfAbsent(wins, new TreeSet<>());
            String teamSameWins = entry.getKey();
            teamsByWins.get(wins).add(teamSameWins);
        }

        return teamsByWins;
    }

    private static void processResult(
            String input, HashMap<String, Set<String>> teamsWithOpponents,
            HashMap<String, Integer> teamsWithWins) {
        String[] params = input.split(" \\| ");
        String host = params[0];
        String guest = params[1];
        teamsWithOpponents.putIfAbsent(host, new TreeSet<>());
        teamsWithOpponents.get(host).add(guest);
        teamsWithOpponents.putIfAbsent(guest, new TreeSet<>());
        teamsWithOpponents.get(guest).add(host);

        String resultFirstLeg = params[2];
        String resultPattern = "(\\d+):(\\d+)";
        Pattern pattern = Pattern.compile(resultPattern);

        int hostGoalsFirstLeg = 0;
        int guestGoalsFirstLeg = 0;
        Matcher match = pattern.matcher(resultFirstLeg);
        if(match.find()) {
            hostGoalsFirstLeg = Integer.parseInt(match.group(1));
            guestGoalsFirstLeg = Integer.parseInt(match.group(2));
        }

        String resultSecondLeg = params[3];
        int hostGoalsSecondLeg = 0;
        int guestGoalsSecondLeg = 0;
        match = pattern.matcher(resultSecondLeg);
        if(match.find()) {
            hostGoalsSecondLeg = Integer.parseInt(match.group(1));
            guestGoalsSecondLeg = Integer.parseInt(match.group(2));
        }

        if(hostGoalsFirstLeg + guestGoalsSecondLeg > hostGoalsSecondLeg + guestGoalsFirstLeg ||
                (hostGoalsFirstLeg + guestGoalsSecondLeg == hostGoalsSecondLeg + guestGoalsFirstLeg &&
                        guestGoalsFirstLeg < guestGoalsSecondLeg)) {
            teamsWithWins.putIfAbsent(host, 0);
            teamsWithWins.replace(host, teamsWithWins.get(host) - 1);
            teamsWithWins.putIfAbsent(guest, 0);
        } else if(hostGoalsFirstLeg + guestGoalsSecondLeg < hostGoalsSecondLeg + guestGoalsFirstLeg ||
                (hostGoalsFirstLeg + guestGoalsSecondLeg == hostGoalsSecondLeg + guestGoalsFirstLeg &&
                        guestGoalsFirstLeg > guestGoalsSecondLeg)) {
            teamsWithWins.putIfAbsent(guest, 0);
            teamsWithWins.replace(guest, teamsWithWins.get(guest) - 1);
            teamsWithWins.putIfAbsent(host, 0);
        }
    }
}
