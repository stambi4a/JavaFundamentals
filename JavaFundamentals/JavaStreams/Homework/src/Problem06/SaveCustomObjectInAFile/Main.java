package Problem06.SaveCustomObjectInAFile;

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static final String pathSource = "resources/Problem06/object.save";
    private static Course[] courses;

    public static void main(String[] args) {
        courses = new Course[5];
        courses[0] = new Course("Java Fundamentals", 400);
        courses[1] = new Course("Advanced C#", 385);
        courses[2] = new Course("JavaSsript Basics", 189);
        courses[3] = new Course("Unity 3D", 150);
        courses[4] = new Course("Data Structures", 350);

        saveObject();
        loadObject();
    }

    private static void saveObject() {
        try (
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathSource))
        ) {
            for(Course course : courses) {
                output.writeObject(course);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private static void loadObject() {
        try (
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathSource))
        ) {
            System.out.println("List of courses:");
            Object object;
            while((object = input.readObject()) != null) {
                System.out.printf("Course:  %s%n", object);
            }
        } catch (EOFException eofe) {

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
