package Problem04.GUnit;

import java.util.HashMap;

public class Method implements Comparable<Method> {
    private String name;
    public HashMap<String, UnitTest> unitTests;

    public Method(String name) {
        this.name = name;
        this.unitTests = new HashMap<>();
    }

    public void addUnitTest(String test) {
        this.unitTests.putIfAbsent(test, new UnitTest(test));
    }

    public int compareTo(Method other) {
        if (this.unitTests.size() == other.unitTests.size()) {
            return this.name.compareTo(other.name);
        }

        return this.unitTests.size() - other.unitTests.size();
    }
}
