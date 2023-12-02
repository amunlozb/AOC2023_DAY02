package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Part1 {
    public static HashMap<String, Integer> createMaxBag() {
        HashMap<String, Integer> bag = new HashMap<>();

        // Problem states: 12 red cubes, 13 green cubes, 14 blue cubes
        bag.put("red", 12);
        bag.put("green", 13);
        bag.put("blue", 14);

        return bag;
    }

    public static Integer processInput() throws IOException {

        HashMap<String, Integer> maxBag = createMaxBag();

        Integer ans = 0;

        FileReader input = new FileReader("src/fakeInput.txt");
        BufferedReader br = new BufferedReader(input);

        String inputLine = br.readLine();

        // Read the input file, line by line
        while (inputLine != null) {

            HashMap<String, Integer> revealedCubes = new HashMap<>();

            // Save the game number:
            Integer gameNumber = (int) inputLine.charAt(5);
            // Remove the beginning of the line that specifices the game number (first X characters):
            inputLine = inputLine.substring(8);
            // Split cube sets
            String[] cubeSets = inputLine.split("; ");

            for (String cubeSet : cubeSets) {
                HashMap<String, Integer> cubeSetMap = getMapFromCubeSet(cubeSet);

                for (Map.Entry<String, Integer> entry : maxBag.entrySet()) {
                    String color = entry.getKey();
                    Integer maxBagValue = maxBag.get(color);
                    Integer currentBagValue = entry.getValue();

                    if (currentBagValue > maxBagValue && currentBagValue != null) {
                        ans += gameNumber;
                    }
                }
            }


            System.out.println("GAME NUMBER: " + gameNumber);

            for (String e : cubeSets) {
                System.out.println("CUBESET: " + e);
            }
            System.out.println("COUNTER: " + ans);
            inputLine = br.readLine();
        }

        return ans;
    }

    // Example cubeset: 1 green, 3 red, 6 blue
    private static HashMap<String, Integer> getMapFromCubeSet(String cubeSet) {
        HashMap<String, Integer> map = new HashMap<>();

        String[] cubeSetArray = cubeSet.split(", ");

        // Example pair: 3 green
        for (String pair : cubeSetArray) {
            Integer numValue = Character.getNumericValue(pair.charAt(0));
            String colorValue = pair.split(" ")[1];

            // Ej map: [(red, 2); (green, 3); (blue, 5)]
            map.put(colorValue, numValue);
        }

        return map;
    }

}
