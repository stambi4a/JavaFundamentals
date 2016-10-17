package Problem04.DragonArmy;

import java.util.*;

public class Main {
    private static final int DEFAULT_DAMAGE = 45;
    private static final int DEFAULT_HEALTH = 250;
    private static final int DEFAULT_ARMOR = 10;
    public static void main(String[] args) {
        registerDragons();
    }

    private static void registerDragons() {
        Scanner scanner = new Scanner(System.in);
        int countLines = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, TreeMap<String, Dragon>> dragons= new LinkedHashMap<>();
        int index = 1;

        while(index <= countLines) {
            String input = scanner.nextLine();
            addDragon(dragons, input);

            index++;
        }

        printDragonRegister(dragons);
    }

    private static void printDragonRegister(LinkedHashMap<String, TreeMap<String, Dragon>> dragons) {
        for(String type : dragons.keySet()) {
            double averageDamage = 0.0d;
            double averageHealth = 0.0d;
            double averageArmor = 0.0d;

            for(Dragon drag : dragons.get(type).values()) {
                averageDamage += drag.getDamage();
                averageHealth += drag.getHealth();
                averageArmor += drag.getArmor();
            }

            int size = dragons.get(type).values().size();
            averageArmor /= size;
            averageDamage /= size;
            averageHealth /= size;
            System.out.printf("%s::(%.2f/%.2f/%.2f)", type, averageDamage, averageHealth, averageArmor);
            System.out.println();
            TreeMap<String, Dragon> drags = dragons.get(type);
            drags.forEach((dragName, dragon) -> {
                System.out.printf(
                        "-%s -> damage: %d, health: %d, armor: %d",
                        dragName, dragon.getDamage(),
                        dragon.getHealth(),
                        dragon.getArmor());
                System.out.println();
            });
        }
    }

    private static void addDragon(
            LinkedHashMap<String, TreeMap<String, Dragon>> dragons,
            String input) {
        String[] params = input.split(" ");
        String dragonType = params[0];
        String dragonName = params[1];
        int damage = DEFAULT_DAMAGE;
        if(!params[2].equals("null")) {
            damage = Integer.parseInt(params[2]);
        }

        int health = DEFAULT_HEALTH;
        if(!params[3].equals("null")) {
            health = Integer.parseInt(params[3]);
        }

        int armor = DEFAULT_ARMOR;
        if(!params[4].equals("null")) {
            armor = Integer.parseInt(params[4]);
        }

        Dragon dragon = new Main().new Dragon(damage, health, armor);
        dragons.putIfAbsent(dragonType, new TreeMap<>());
        dragons.get(dragonType).put(dragonName, dragon);
    }

    public class Dragon {

        private int damage;
        private int health;
        private int armor;

        public Dragon(int damage, int health, int armor) {
            this.setDamage(damage);
            this.setHealth(health);
            this.setArmor(armor);
        }

        public int getDamage() {

            return this.damage;
        }

        public void setDamage(int value) {
            this.damage = value;
        }

        public int getHealth() {

            return this.health;
        }

        public void setHealth(int value) {
            this.health = value;
        }

        public int getArmor() {

            return this.armor;
        }

        public void setArmor(int value) {
            this.armor = value;
        }
    }
}
