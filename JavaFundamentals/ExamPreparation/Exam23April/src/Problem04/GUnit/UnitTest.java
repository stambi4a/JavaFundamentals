package Problem04.GUnit;

public class UnitTest implements Comparable<UnitTest> {
    private String name;

    public UnitTest(String name) {
        this.name = name;
    }

    public int compareTo(UnitTest other) {
        if (this.name.length() == other.name.length()) {
            return this.name.compareTo(other.name);
        }

        return this.name.length() - other.name.length();
    }
}
