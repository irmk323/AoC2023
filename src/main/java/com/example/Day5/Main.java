package com.example.Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day5/lines.text";
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arr = fileContent.split("\n\n");
        long[] seeds = Arrays.stream(arr[0].split("\\s+"))
                        .skip(1)
                        .mapToLong(Long::parseLong)
                        .toArray();

        for(int i =1; i< arr.length; i++){
            List<List<Long>> ranges = Arrays.stream(arr[i].split("\n"))
            .filter(line -> !line.endsWith(" map:"))
            .map(line -> Arrays.stream(line.split("\\s+"))
                                .map(Long::parseLong)
                                .collect(Collectors.toList()))
            .collect(Collectors.toList());

            
            for(int j = 0; j< seeds.length; j++){
                for(List<Long> range: ranges){
                    long a = range.get(0);
                    long b = range.get(1);
                    long c = range.get(2);
                    if (b <=  seeds[j] && seeds[j] < b+c){
                        //  seeds[j] -b +a;
                        // if x ( seeds  = 99), 99-98 +50
                      
                        // seeds ------------------------98,99-----
                        // soil --------------50,51------
                        seeds[j] = seeds[j] -b +a;
                        break;
                    }else{
                        seeds[j] = seeds[j];
                    }
                }
            }

        }
        long minValue = Arrays.stream(seeds)
                        .min()
                        .orElse(0);
        System.err.println(minValue);
        
    }
}


