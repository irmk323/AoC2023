package com.example.Day1;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/lines.text";
        int ans = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line using BufferedReader
            while ((line = reader.readLine()) != null) {
                
                String extractedNumStr = extractDigits(line);
                System.out.println(line + ":   "+  Integer.valueOf(extractedNumStr));
                int twoDigit = makingTwo(extractedNumStr);
                ans += (twoDigit);
            }         
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ans);
    }
    private static String extractDigits(String input){
        return input.replaceAll("[^0-9]", "");
    }
    private static int makingTwo(String extractedNumStr){
        int digit = extractedNumStr.length();
        int twoDigit = 0;
        if (digit == 1 ){
            int num = Integer.valueOf(extractedNumStr);
            twoDigit = num + (num * 10);
            return twoDigit;
        }else if(digit == 2){
            return Integer.valueOf(extractedNumStr);
        }else{
            String firstChar = extractedNumStr.substring(0, 1);
            String lastChar = extractedNumStr.substring(digit-1);
            String result = firstChar + lastChar;
            twoDigit = Integer.valueOf(result);

        }
        return twoDigit;

    }
}