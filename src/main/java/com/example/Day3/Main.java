package com.example.Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day3/lines.text";
        // String filePath = "lines.text";
        int ans = 0;
        List<List<Character>> matrix = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                makingMatrix(matrix, line);
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(List<Character> integ: matrix){
            // System.out.println(integ);
        }
        

        int row = matrix.size();
        int col = matrix.get(0).size();
        System.out.println( "row" + row + "col " + col);
        List<Long> sumCandidate = new ArrayList<>();
        LinkedHashMap<String, Boolean> charMap = new LinkedHashMap<>();

        for(int i = 0; i< row ; i++){
            for(int j = 0; j< col ;j++){
                if(Character.isDigit(matrix.get(i).get(j))){
                    String cur = String.valueOf(matrix.get(i).get(j));
                    if(charMap.containsKey(cur)){
                         charMap.put(cur + ":", isCharhasSignalInNaighbor(charMap , matrix, i , j)); 
                    }else{
                        charMap.put(cur , isCharhasSignalInNaighbor(charMap , matrix, i , j)); 
                    }
                }else{
                    if(charMap.size() > 0 && IsValidNumToSum(charMap)  ){

                        String candidate = charMap.keySet().stream().
                        map(Object::toString).collect(Collectors.joining());
                        String replace = candidate.replace(":", "");
                        sumCandidate.add(Long.valueOf(replace));
                        charMap.clear();
                    }else{
                        charMap.clear();
                    }
                }
            }
             charMap.clear();
            
        }
        //  System.out.println(sumCandidate);
        long sum = sumCandidate.stream().mapToLong(Long::longValue).sum();
        System.out.println("sum is " + sum);
    }
    private static boolean IsValidNumToSum(LinkedHashMap<String, Boolean> charMap){
        boolean ans = false;
        for(Map.Entry<String, Boolean> mapEntry : charMap.entrySet()) {
            ans |= mapEntry.getValue();
        }
        return ans;
    }

    private static boolean isRange(List<List<Character>> matrix , int i, int j){
        if(i >=0 && i< matrix.size()-1 && j >= 0 && j < matrix.get(0).size()-1 ){
            return true;
        }
        return false;
    }
    private static boolean isCharhasSignalInNaighbor(LinkedHashMap<String, Boolean> charMap,List<List<Character>> matrix , int i, int j  ){
        int[][] directions = {{-1, 0},{1, 0},{0, -1},{0, 1},{1, 1},{-1, -1},{-1, 1},{1, -1} };
        boolean ans = false;
        for(int[] d : directions){

            if(isRange(matrix, i + d[0], j+ d[1])){ 
                char singleChar = matrix.get(i+d[0] ).get(j+d[1]);
                if(!(Character.isDigit(singleChar) || singleChar == '.')){
                    ans = true;
                    break;
                }

            }
        }
        return ans;
    }

    private static void makingMatrix(List<List<Character>> matrix, String line){
        List<Character> row = new ArrayList<>();
       for(Character c : line.toCharArray()){
            row.add(c);
       }
       matrix.add(row);
    }
}

