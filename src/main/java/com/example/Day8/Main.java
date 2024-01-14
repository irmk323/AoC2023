package com.example.Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Main {

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
        int part1 = solution(orderList, relations);
        System.out.println("part1: " +part1);
    }
    public static int solution( String[] orderList ,  String[] relations){
        Map<String, Pair> graph = new HashMap<>();
        for(String relation: relations){
        Pattern pattern = Pattern.compile("([A-Z]+) = \\(([A-Z]+), ([A-Z]+)\\)");
        Matcher matcher = pattern.matcher(relation);
            if (matcher.find()) {
                graph.put(matcher.group(1), new Pair(matcher.group(2), matcher.group(3)));
            }    
        }
        int count = 0;
        String cur = "AAA";

        while(!cur.equals("ZZZ")){
            for(String order: orderList){
                if(order.equals("L")){
                    cur = graph.get(cur).left;
                }else{
                    cur = graph.get(cur).right;
                }
                if(cur.equals("ZZZ")){
                    break;
                }
            }
            count++;
        }
        return count * orderList.length;

    }
}

