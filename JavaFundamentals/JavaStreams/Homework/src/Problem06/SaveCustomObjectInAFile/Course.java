package Problem06.SaveCustomObjectInAFile;

import java.io.Serializable;

public class Course  implements Serializable {
    private String name;
    private int numberOfStudents;

    public Course(String name, int number) {
        this.setName(name);
        this.setNumberOfStudents(number);
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if(name == null || name.length() < 3) {
            throw new IllegalArgumentException("Name should be at least three letters long.");
        }

        this.name = name;
    }

    public Integer getNumberOfStudents() {
        return this.numberOfStudents;
    }

    public void setNumberOfStudents(int number) {
        if(number < 0) {
            throw new IllegalArgumentException("Number of students should be non-negative number.");
        }

        this.numberOfStudents = number;
    }
    @Override
    public String toString () {
        return String.format("Name: %-25s Number Of Students: %d", this.getName(), this.getNumberOfStudents());
    }
}
