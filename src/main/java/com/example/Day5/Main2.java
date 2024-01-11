    package com.example.Day5;


    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
    

    public class Main2 {
        public static void main(String[] args) {
            String filePath = "src/main/java/com/example/Day5/lines.text";
            String fileContent = "";
            try {
                fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] arr = fileContent.split("\n\n");
            String[] seedValues = arr[0].split("seeds:")[1].trim().split("\\s+");
        
            List<long[]> seeds = new ArrayList<>();
            for (int i = 0; i < seedValues.length; i += 2) {
                seeds.add(new long[]{Long.parseLong(seedValues[i]), Long.parseLong(seedValues[i])+ Long.parseLong(seedValues[i + 1])});
            }

            for(int i =1; i< arr.length; i++){

                List<long[]> ranges = Arrays.stream(arr[i].split("\n"))
                        .filter(line -> !line.endsWith(" map:"))
                        .map(line -> Arrays.stream(line.split("\\s+"))
                                .mapToLong(Long::parseLong)
                                .toArray())
                        .collect(Collectors.toList());

                List<long[]> newSeeds = new ArrayList<>();
                while (!seeds.isEmpty()) {
                    long[] seed = seeds.remove(0);
                    long s = seed[0], e = seed[1];
                    boolean conditionMet = false;
                    for (long[] range : ranges) {
                        long a = range[0], b = range[1], c = range[2];
                        long os = Math.max(s, b);
                        long oe = Math.min(e, b + c);
                        if (os < oe) {
                            conditionMet =true;
                            newSeeds.add(new long[]{os - b + a, oe - b + a});
                            if (os > s) {
                                seeds.add(new long[]{s, os});
                            }
                            if (e > oe) {
                                seeds.add(new long[]{oe, e});
                            }
                            break;
                        }
                        
                    }
                    if(!conditionMet){
                        newSeeds.add(new long[]{s, e});
                    }        
                    System.out.println(newSeeds);
                    
                }
                seeds = newSeeds;
          
                
            }
            System.out.println(seeds);
            for(long[] seed: seeds){
                System.out.println(seed[0]+ ":" + seed[1]);
            }
            seeds.sort(Comparator.comparingLong(array -> array[0]));
            System.out.println(seeds.get(0)[0]);
            
        }
    }


