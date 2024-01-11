package com.example.Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
        public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day4/lines.text";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<Integer, Long> ansMap = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                String[] arr= line.split(" \\| ");
                int givenCard = Integer.valueOf(arr[0].split(": ")[0].split("\\s+")[1]);
                String[] givenCardNums =  arr[0].split(": ")[1].split("\\s+");
                String[] hitNums = arr[1].split("\\s+");
                
                long count = Arrays.stream(givenCardNums).filter(Arrays.asList(hitNums)::contains).count();
                System.out.println(count);
                ansMap.put(givenCard, count);

            }
            // ansMap.entrySet().stream().map(Map.Entry::getKey).forEach(System.out::println);
            long result = ansMap.entrySet().stream().mapToLong(entry ->(long)Math.pow(2, entry.getValue()-1)).sum();
            System.out.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
