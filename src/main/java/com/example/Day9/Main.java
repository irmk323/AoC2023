package com.example.Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day9/line.text";
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = fileContent.split("\n");
        long part1 = solution(arr, "p1");
        long part2 = solution(arr, "p2");
        System.out.println("part1: " +part1);
        System.out.println("part2: " +part2);
    }
    public static long solution( String[] arr , String part){
        long sum = 0;
        for(String str: arr){
            List<Integer> intList = Arrays.stream(str.split(" "))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());
            List<Integer> ans = new ArrayList<>();
            int elem = 0;
            if(part == "p1"){
                ans = helper(intList);
                elem = ans.get(ans.size()-1);
            }else{
                ans = helper2(intList);
                elem = ans.get(0);
            }
            sum+= elem;
        }
        return sum;

    }

    private static List<Integer> helper(List<Integer> nums){
        if(isAllZero(nums)){
            nums.add(0);
            return nums;
        }
        List<Integer> subArr = new ArrayList<>();
        for(int i =0; i+1 < nums.size(); i++){
            subArr.add(nums.get(i+1) - nums.get(i));
        }
        List<Integer> prev =  helper(subArr);
        nums.add(nums.get(nums.size()-1) + prev.get(prev.size()-1));
        return nums;
    }

    private static List<Integer> helper2(List<Integer> nums){
        if(isAllZero(nums)){
            nums.add(0, 0);
            return nums;
        }
        List<Integer> subArr = new ArrayList<>();
        for(int i = nums.size() -1;  i-1>=0; i--){
            subArr.add(0, nums.get(i) - nums.get(i-1));
        }
        List<Integer> prev =  helper2(subArr);
        nums.add(0, nums.get(0) - prev.get(0));
        return nums;
    }

    private static boolean isAllZero(List<Integer> numbers){
        return numbers.stream().allMatch(num -> num== 0);
    }
}


