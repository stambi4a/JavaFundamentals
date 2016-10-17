package Problem04.GUnit;

import java.util.HashMap;

public class Class implements Comparable<Class> {
    private String name;
    public HashMap<String, Method> methods;
    private int unitTestsCount;

    public Class(String name) {
        this.name = name;
        this.methods = new HashMap<>();
        this.unitTestsCount = 0;
    }

    public void addMethod(String method) {
        this.methods.putIfAbsent(method, new Method(method));
    }

    public int compareTo(Class other) {
        if (this.unitTestsCount == other.unitTestsCount) {
            if (this.methods.size() == other.methods.size()) {
                return this.name.compareTo(other.name);
            }

            return this.methods.size() - other.methods.size();
        }

        return other.unitTestsCount - this.unitTestsCount;
    }
}
