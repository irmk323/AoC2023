package com.example.Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day6/lines.text";
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = fileContent.split("\n");
        int[] timeForPart1 = Arrays.stream(arr[0].replace("Time:", "").trim().split("\\s+"))
                        .mapToInt(Integer::parseInt).toArray();
        int[] recordForPart1 = Arrays.stream(arr[1].replace("Distance:", "").trim().split("\\s+"))
                        .mapToInt(Integer::parseInt).toArray();

        long timeForPart2 = Long.parseLong(arr[0].replaceAll("[^0-9]", ""));
        long recordForPart2 = Long.parseLong(arr[1].replaceAll("[^0-9]", ""));

        int res1 = part1(timeForPart1, recordForPart1);
        int res2 = part2(timeForPart2, recordForPart2);
        System.out.println(res1);
        System.out.println(res2);
    }
    private static int part1( int[] time,  int[] record){
        int total = 1;
        for(int i = 0; i< time.length; i++){
            int count = 0;
            for(int j= 1; j< time[i]; j++){
                int remain = time[i] - j;
                if(remain * j  > record[i]){
                    count++;
                }
            }
            total *= count;
        }
        return total;
    }
    private static int part2( long time , long record){
        int totalCount = 0;
            for(long j= 1; j< time; j++){
                long remain = time - j;
                if(remain * j  > record){
                    totalCount++;
                }
            }
        return totalCount;
    }
}
