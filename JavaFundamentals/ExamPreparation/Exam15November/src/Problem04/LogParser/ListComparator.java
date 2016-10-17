package Problem04.LogParser;

import java.util.Comparator;

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
