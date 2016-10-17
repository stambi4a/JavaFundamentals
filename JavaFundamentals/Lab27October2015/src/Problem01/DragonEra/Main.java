package Problem01.DragonEra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DRAGON_NAME_PREFIX = "Dragon_";

    public static int dragonsCount = 0;
    public static void main(String[] args) {
        registerDragons();
    }
    private static void printDragons(List<Dragon> dragons, int countSpaces) {
        for(Dragon dragon : dragons) {
            String spaces = String.join("", Collections.nCopies(countSpaces, " "));
            if(dragon.getIsAlive()) {
                System.out.println(spaces + dragon);
            }

            printDragons(dragon. getChildren(), countSpaces + 2);
        }
    }

    private static void passAge(Dragon dragon, YearType yearFactor) {
        dragon.age();
        dragon.lay();

        if(dragon.getIsAlive()) {
            for(Egg egg : dragon.getEggs()) {
                egg.setYearFactor(yearFactor);

                egg.age();
                egg.hatch();
            }
        }

        for(Dragon child : dragon.getChildren()) {
            passAge(child, yearFactor);
        }
    }

    private static void registerDragons()
    {
        List<Dragon> dragons = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int dragonStart = Integer.parseInt(scanner.nextLine());
        for(int i = 1; i <= dragonStart; i++)
        {
            Dragon dragon = new Dragon(DRAGON_NAME_PREFIX + i, 0);
            int dragonEggs = Integer.parseInt(scanner.nextLine());
            for(int j = 0; j < dragonEggs; j++) {
                Egg egg = new Egg(0, dragon);
                dragon.lay(egg);
            }

            dragons.add(dragon);
        }

        dragonsCount = dragonStart + 1;
        int years = Integer.parseInt(scanner.nextLine());
        for(int year = 1; year <= years; year++)
        {
            String yearType = scanner.nextLine();
            YearType yearFactor = YearType.valueOf(yearType);
            for(Dragon  dragon : dragons) {
                passAge(dragon, yearFactor);
            }
        }

        printDragons(dragons, 0);
    }
}

class Dragon {
    private static final int AGE_DEATH = 6;
    private static final int AGE_START_LAYING_EGGS = 3;
    private static final int AGE_STOP_LAYING_EGGS = 4;

    private String name;
    private int age;
    private boolean isAlive;
    private List<Dragon> children;
    private List<Egg> eggs;
    public Dragon(String name, int age) {
        this.setName(name);
        this.setAge(age);
        this.setIsAlive(true);
        this.children = new ArrayList<>();
        this.eggs = new ArrayList<>();
    }

    public List<Dragon> getChildren() {
        return this.children;
    }

    public void setChildren(Dragon dragon) {
        this.getChildren().add(dragon);
    }

    public List<Egg> getEggs() {
        return this.eggs;
    }

    public void setEggs(Egg egg) {
        this.eggs.add(egg);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void lay(Egg egg) {
        this.setEggs(egg);
    }

    public void age() {
        if(this.isAlive) {
            this.setAge(this.getAge() + 1);
        }

        if(this.getAge() == AGE_DEATH) {
            this.setIsAlive(false);
        }
    }

    public void lay() {
        if(this.getAge() >= AGE_START_LAYING_EGGS && this.getAge() <= AGE_STOP_LAYING_EGGS) {
            Egg egg = new Egg(-1, this);
            this.setEggs(egg);
        }
    }

    public void increaseOffSpring(Dragon dragon) {
        this.setChildren(dragon);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

class Egg {
    private static final String DRAGON_NAME_PREFIX = "Dragon_";
    private static final int AGE_HATCH = 2;

    private int age;
    private Dragon parent;
    private YearType yearFactor;

    public Egg(int age, Dragon parent) {
        setAge(age);
        this.setParent(parent);
    }

    public YearType getYearFactor() {
        return this.yearFactor;
    }

    public void setYearFactor(YearType yearType) {
        this.yearFactor = yearType;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dragon getParent() {
        return this.parent;
    }

    public void setParent(Dragon parent) {
        this.parent = parent;
    }

    public void age() {
        this.setAge(this.getAge() + 1);
    }

    public void hatch() {
        if(this.getAge() == AGE_HATCH) {
            int yearFactor = this.getYearFactor().ordinal();
            for(int i = 0; i < yearFactor; i++) {
                Dragon baby = new Dragon(
                        this.parent.getName() + "/" + DRAGON_NAME_PREFIX + Main.dragonsCount, - 1);
                this.parent.increaseOffSpring(baby);
                Main.dragonsCount++;
            }
        }
    }
}

enum YearType {
    Bad,
    Normal,
    Good
}
