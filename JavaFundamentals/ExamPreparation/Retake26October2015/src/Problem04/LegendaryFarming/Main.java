package Problem04.LegendaryFarming;

import java.util.*;

public class Main {
    private static final int LEGENDARY_ITEM_PRICE = 250;
    public static void main(String[] args) {
        collectResources();
    }

    private static void collectResources() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> keyResources = new HashMap<>();
        keyResources.put("fragments", 0);
        keyResources.put("motes", 0);
        keyResources.put("shards", 0);
        TreeMap<String, Integer> junkResources = new TreeMap<>();
        String raceWinner = null;
        while(true) {
            String input = scanner.nextLine().toLowerCase();
            if(input == null) {
                break;
            }

            String[] params = input.split(" ");
            raceWinner = addResources(params, keyResources, junkResources);
            if(raceWinner != null) {
                break;
            }
        }

        TreeMap<Integer, TreeSet<String>> resourcesByCount = sortKeyResourcesByCount(keyResources);
        printResult(raceWinner, resourcesByCount, junkResources);
    }

    private static TreeMap<Integer, TreeSet<String>> sortKeyResourcesByCount(HashMap<String, Integer> keyResources) {
        List<Integer> keyResourcesByCount = new ArrayList();
        for(Integer resourceByCount : keyResources.values()) {
            keyResourcesByCount.add(resourceByCount);
        }

        TreeMap<Integer, TreeSet<String>> resourcesByCount =
                new TreeMap<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                });

        for(String keyResource : keyResources.keySet()) {
            int value = keyResources.get(keyResource);
            resourcesByCount.putIfAbsent(value, new TreeSet<>());
            resourcesByCount.get(value).add(keyResource);
            /*System.out.println(keyResource + ": " + keyResources.get(keyResource) );*/
        }

        return resourcesByCount;
    }

    private static void printResult(
            String raceWinner,
            TreeMap<Integer, TreeSet<String>> keyResources,
            TreeMap<String, Integer> junkResources) {
        String legendaryItem = null;
        if(raceWinner.equals("fragments")) {
            legendaryItem = "Valanyr";
        }
        if(raceWinner.equals("shards")) {
            legendaryItem = "Shadowmourne";
        }

        if(raceWinner.equals("motes")) {
            legendaryItem = "Dragonwrath";
        }

        System.out.println(legendaryItem + " obtained!");

        for(Integer resourceCount : keyResources.keySet()) {
            for(String resource : keyResources.get(resourceCount)) {
                System.out.println(resource + ": " + resourceCount);
            }


        }

        for(String junkResource : junkResources.keySet()) {
            System.out.println(junkResource + ": " + junkResources.get(junkResource));

        }
    }

    private static String addResources(
            String[] params,
            HashMap<String, Integer> keyResources,
            TreeMap<String, Integer> junkResources) {
        int length = params.length;
        for(int i = 0; i < length; i = i + 2) {
            String resource = params[i + 1];
            int count = Integer.parseInt(params[i]);
            if(resource.equals("fragments") ||
                    resource.equals("motes") ||
                    resource.equals("shards")) {
                int resourceCount = keyResources.get(resource) + count;
                keyResources.put(resource, resourceCount);
                if(resourceCount >= 250) {
                    resourceCount -= 250;
                    keyResources.put(resource, resourceCount);
                    return resource;
                }

            } else {
                junkResources.putIfAbsent(resource, 0);
                int resourceCount = junkResources.get(resource) + count;
                junkResources.put(resource, resourceCount);
            }
        }

        return null;
    }
}
