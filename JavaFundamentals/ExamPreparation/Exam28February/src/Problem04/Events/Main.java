package Problem04.Events;

import javafx.collections.transformation.SortedList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        registerEvents();
    }

    private static void registerEvents() {
        Scanner scanner = new Scanner(System.in);
        Integer countLines = Integer.parseInt(scanner.nextLine());
        HashMap<String, TreeMap<String, List<Date>>> events = new HashMap<>();
        int index = 1;
        while(index <= countLines) {
            String input = scanner.nextLine();
            addEvents(events, input);
            index++;
        }

        TreeSet<String> eventsToBePrinted = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        String[] eventNames = scanner.nextLine().split(",");
        int length = eventNames.length;
        for(int i = 0; i < length; i++) {
            eventsToBePrinted.add(eventNames[i]);
        }

        printEvents(events, eventsToBePrinted);
    }

    private static void printEvents(
            HashMap<String, TreeMap<String, List<Date>>> events,
            TreeSet<String> eventsToBePrinted) {
       eventsToBePrinted.forEach((event)-> {
           if(events.containsKey(event)) {
               System.out.printf("%s:%n", event);
               int index = 0;
               TreeMap<String, List<Date>> people = events.get(event);
               for(Map.Entry<String, List<Date>> person: people.entrySet()) {
                   index++;
                   System.out.printf("%d. %s -> ", index, person.getKey());
                   Collections.sort(person.getValue());
                   List<Date> eventTimes = person.getValue();
                   int size = eventTimes.size();
                   int indexTime = 1;
                   for(Date time : eventTimes) {
                       if(indexTime < size) {
                           System.out.printf("%1$tH:%1$tM, ", time);
                       } else {
                           System.out.printf("%1$tH:%1$tM", time);
                       }

                       indexTime++;
                  }

                   System.out.println();
               }
           }
       });
    }

    private static void addEvents(HashMap<String, TreeMap<String, List<Date>>> events, String input) {
        String properInputPattern = "^#[A-Za-z]+:\\s*@[A-Za-z]+\\s*\\d{1,2}:\\d{1,2}+$";
        String properInput = matchInput(input, properInputPattern);
        if(properInput == null) {
            return;
        }

        String personPattern = "(?<=#)([a-zA-Z]+)(?=:)";
        String person = matchInput(input, personPattern);
        if(person == null) {
            return;
        }

        String locationPattern = "(?<=@)([a-zA-Z]+)";
        String location = matchInput(input, locationPattern);
        if(location == null) {
            return;
        }

        String timePattern = "(\\d+):(\\d+)";
        String time = parseTime(input, timePattern);
        if(time == null) {
            return;
        }

        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date eventTime = timeFormat.parse(time);
            events.putIfAbsent(location, new TreeMap<>());
            events.get(location).putIfAbsent(person, new ArrayList<>());
            events.get(location).get(person).add(eventTime);
        } catch (ParseException pe) {
            return;
        }
    }

    private static String matchInput(String input, String matchPattern) {
        Pattern pattern = Pattern.compile(matchPattern);
        Matcher match = pattern.matcher(input);
        String matched = null;
        int index = 0;
        while(match.find()) {
            index++;
            if(index == 2) {
                return null;
            }
            matched =  match.group();
        }

        return matched;
    }

    private static String parseTime(String input, String timePattern) {
        Pattern pattern = Pattern.compile(timePattern);
        Matcher match = pattern.matcher(input);
        String time = null;
        int hours = 0;
        int minutes = 0;
        int index = 0;
        while(match.find()) {
            index++;
            if (index > 1) {
                return null;
            }

            time = match.group();
            hours = Integer.parseInt(match.group(1));
            minutes = Integer.parseInt(match.group(2));
        }

        if(index == 0) {
            return null;
        }

        try {
            if(hours > 23 || hours < 0 || minutes < 0 || minutes > 59) {
                return null;
            }
            return time;
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}