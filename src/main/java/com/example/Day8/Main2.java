package com.example.Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main2 {

    static class Pair{
        // String root;
        String right;
        String left;
        public Pair(String left, String right){
            this.left = left;
            this.right = right;
        }
        public String getLeft() {
            return left;
        }
    
        public String getRight() {
            return right;
        }
    }
    private static final Pattern RELATION_PATTERN = Pattern.compile("([A-Z0-9]+) = \\(([A-Z0-9]+), ([A-Z0-9]+)\\)");
    
    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day8/line.text";
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = fileContent.split("\n\n");
        String[] orderList = arr[0].split("");
        String[] relations =  arr[1].split("\n");
        long part2 = solution(orderList, relations);
        System.out.println("part2: " +part2);
    }
    public static long solution( String[] orderList ,  String[] relations){
        Map<String, Pair> graph = new HashMap<>();
        List<String> aSet = new LinkedList<>();
        for(String relation: relations){
        Matcher matcher = RELATION_PATTERN.matcher(relation);
            if (matcher.find()) {
                graph.put(matcher.group(1), new Pair(matcher.group(2), matcher.group(3)));
                if(matcher.group(1).endsWith("A")){
                    aSet.add(matcher.group(1));
                }
            }
        }
        
        List<Long> countList = new ArrayList<>();
        for(String set: aSet){
            String cur = set;
            long count = 0;
            while(!cur.endsWith("Z")){
                for(String order: orderList){
                    if(cur.endsWith("Z")){
                        break;
                    }
                    if(order.equals("L")){
                        cur = graph.get(cur).left;
                        count++;
                    }else{
                        cur =  graph.get(cur).right;
                        count++;
                    }
                }
                
            }
            countList.add(count);
        }
        return findLCMOfList(countList);

    }

    static long findLCM(long a, long b) {
        return (a * b) / findGCD(a, b);
    }
    
    static long findGCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    static long findLCMOfList(List<Long> numbers) {
        long lcm = numbers.get(0);
        for (long i = 1; i < numbers.size(); i++) {
            lcm = findLCM(lcm, numbers.get((int)i));
        }
        return lcm;
    }
}

