package Problem04.ChampionsLeague;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupCount = scanner.nextInt();
        scanner.nextLine();
        int index = 1;
        int linesCount = groupCount * 2;
        TreeMap<String, TreeMap<String, Team>> teams = new TreeMap<>();
        while (index <= linesCount) {
            index++;
            String[] params = scanner.nextLine().split("\\s+-\\s+");
            if (params.length != 4) {
                continue;
            }

            String namePattern = "[A-Z][a-z]{2,}";
            Pattern pattern = Pattern.compile(namePattern);
            int i;
            for (i = 0; i < 3; i++) {
                Matcher match = pattern.matcher(params[i]);
                if (match.find()) {
                    if (match.group().length() < params[i].length()) {
                        break;
                    }
                } else {
                    break;
                }
            }

            if (index < 3) {
                continue;
            }

            int scored = 0;
            int conceived = 0;
            namePattern = "(\\d+):(\\d+)";
            pattern = Pattern.compile(namePattern);
            Matcher match = pattern.matcher(params[3]);
            if (match.find()) {
                if (match.group().length() != params[3].length()) {
                    continue;
                }
                scored = Integer.parseInt(match.group(1));
                conceived = Integer.parseInt(match.group(2));
            } else {
                continue;
            }

            String groupName = params[0];
            String host = params[1];
            String guest = params[2];

            teams.putIfAbsent(groupName, new TreeMap<>());
            Team hostTeam = new Team(host, 1, 0, 0, 0, scored, conceived, scored - conceived, 0);
            if(scored > conceived) {
                hostTeam.setMatchesWon(1);
                hostTeam.setPoints(3);
            } else if(scored == conceived) {
                hostTeam.setMatchesDrawed(1);
                hostTeam.setPoints(1);
            } else {
                hostTeam.setMatchesLost(1);
            }
            if(teams.get(groupName).containsKey(host)) {
                hostTeam.setMatchesPlayed(teams.get(groupName).get(host).getMatchesPlayed() + hostTeam.getMatchesPlayed());
                hostTeam.setMatchesWon(teams.get(groupName).get(host).getMatchesWon() + hostTeam.getMatchesWon());
                hostTeam.setMatchesDrawed(teams.get(groupName).get(host).getMatchesDrawed() + hostTeam.getMatchesDrawed());
                hostTeam.setMatchesLost(teams.get(groupName).get(host).getMatchesLost() + hostTeam.getMatchesLost());
                hostTeam.setScored(teams.get(groupName).get(host).getScored() + hostTeam.getScored());
                hostTeam.setConceived(teams.get(groupName).get(host).getConceived() + hostTeam.getConceived());
                hostTeam.setGoalDifference(teams.get(groupName).get(host).getGoalDifference() + hostTeam.getGoalDifference());
                hostTeam.setPoints(teams.get(groupName).get(host).getPoints() + hostTeam.getPoints());
            }

            teams.get(groupName).put(host, hostTeam);

            Team guestTeam = new Team(guest, 1, 0, 0, 0, conceived, scored, conceived - scored, 0);
            if(conceived > scored) {
                hostTeam.setMatchesWon(1);
                hostTeam.setPoints(3);
            } else if(scored == conceived) {
                hostTeam.setMatchesDrawed(1);
                hostTeam.setPoints(1);
            } else {
                hostTeam.setMatchesLost(1);
            }
            if(teams.containsKey(guest)) {
                guestTeam.setMatchesPlayed(teams.get(groupName).get(guest).getMatchesPlayed() + hostTeam.getMatchesPlayed());
                guestTeam.setMatchesWon(teams.get(groupName).get(guest).getMatchesWon() + hostTeam.getMatchesWon());
                guestTeam.setMatchesDrawed(teams.get(groupName).get(guest).getMatchesDrawed() + hostTeam.getMatchesDrawed());
                guestTeam.setMatchesLost(teams.get(groupName).get(guest).getMatchesLost() + hostTeam.getMatchesLost());
                guestTeam.setScored(teams.get(groupName).get(guest).getScored() + hostTeam.getScored());
                guestTeam.setConceived(teams.get(groupName).get(guest).getConceived() + hostTeam.getConceived());
                guestTeam.setGoalDifference(teams.get(groupName).get(guest).getGoalDifference() + hostTeam.getGoalDifference());
                guestTeam.setPoints(teams.get(groupName).get(guest).getPoints() + hostTeam.getPoints());
            }

            teams.get(groupName).put(guest, guestTeam);
        }

        printStandings(teams);
    }

    private static void printStandings(TreeMap<String, TreeMap<String, Team>> teams){
        for(String group : teams.keySet()) {
            System.out.printf("%35s%n", group);
            System.out.printf(
                    "%1$25s%2$5s%3$5s%4$5s%5$5s%6$5s%7$5s%8$5s%9$5s%n",
                    "Team",
                    "P",
                    "W",
                    "D",
                    "L",
                    "F",
                    "A",
                    "+/-",
                    "pts");
            for(Team team : teams.get(group).values()) {
                System.out.println(team);
            }
        }

        System.out.println();
    }
}

class Team implements Comparable<Team>{
    private String name;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesDrawed;
    private int matchesLost;
    private int scored;
    private int conceived;
    private int goalDifference;
    private int points;
    public Team(
            String name,
            int matchesPlayed,
            int matchesWon,
            int matchesDrawed,
            int matchesLost,
            int scored,
            int conceived,
            int goalDifference,
            int points) {
        this.setName(name);
        this.setMatchesPlayed(matchesPlayed);
        this.setMatchesWon(matchesWon);
        this.setMatchesDrawed(matchesDrawed);
        this.setMatchesLost(matchesLost);
        this.setScored(scored);
        this.setConceived(conceived);
        this.setGoalDifference(goalDifference);
        this.setPoints(points);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public int getMatchesPlayed() {
        return this.matchesPlayed;
    }

    public void setMatchesPlayed(int value) {
        this.matchesPlayed = value;
    }

    public int getMatchesWon() {
        return this.matchesWon;
    }

    public void setMatchesWon(int value) {
        this.matchesWon = value;
    }

    public int getMatchesDrawed() {
        return this.matchesDrawed;
    }

    public void setMatchesDrawed(int value) {
        this.matchesDrawed = value;
    }

    public int getMatchesLost() {
        return this.matchesLost;
    }

    public void setMatchesLost(int value) {
        this.matchesLost = value;
    }

    public int getGoalDifference() {
        return this.goalDifference;
    }

    public void setGoalDifference(int value) {
        this.goalDifference = value;
    }

    public int getScored() {
        return this.scored;
    }

    public void setScored(int value) {
        this.scored = value;
    }

    public int getConceived() {
        return this.conceived;
    }

    public void setConceived(int value) {
        this.conceived = value;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int value) {
        this.points = value;
    }

    public int compareTo(Team other) {
        if(this.points == other.points) {
            if(this.goalDifference == other.goalDifference) {
                if(this.scored == other.scored) {
                    Random random = new Random();
                    while(true) {
                        int thisChoice = random.nextInt(2);
                        int otherChoice = random.nextInt(2);
                        if(thisChoice != otherChoice) {
                            return otherChoice - thisChoice;
                        }
                    }
                }

                return other.scored - this.scored;
            }

            return other.goalDifference - this.goalDifference;
        }

        return other.points - this.points;
    }
    @Override
    public String toString() {
        return String.format(
                "%1$25s%2$5d%3$5d%4$5d%5$5d%6$5d%7$5d%8$5d%9$5d",
                this.name,
                this.matchesPlayed,
                this.matchesWon,
                this.matchesDrawed,
                this.matchesLost,
                this.scored,
                this.conceived,
                this.goalDifference,
                this.points);
    }
}
