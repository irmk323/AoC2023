// package com.example.Day1;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.List;
// import java.util.TreeMap;
// import java.util.stream.Collectors;

// public class Main3 {
//     private static List<String> words = List.of(
//         "zero",
//         "one",
//         "two",
//         "three",
//         "four",
//         "five",
//         "six",
//         "seven",
//         "eight",
//         "nine"
//     );

//     public static void main(String[] args) {
//         String filePath = "src/main/resources/lines.text";
//         int ans = 0;
//         // try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//         //     String line;
//         //     // Read each line using BufferedReader
//         //     // 3369eightnine89
//         //     while ((line = reader.readLine()) != null) {
//         //         // System.out.println(line);
//         //         int extractFirst = extractDigits(line, true);
//         //         int extractSecond = extractDigits(line, false);
//         //         int num = (extractFirst*10) + extractSecond;
//         //         System.out.println(line + " : " +  extractFirst + " : " + extractSecond + ":" + num);
//         //         ans += num;
//         //     }         
//         // } catch (Exception e) {
//         //     e.printStackTrace();
//         // }
//         System.out.println(ans);
//          System.out.println(extractDigits("eightwothree", true));
         
//         System.out.println(extractDigits("3369eight893nine", false));
        
//     }

//     private static String reverse(String str) { return new StringBuilder(str).reverse().toString(); }

//     private static int extractDigits(String line , boolean isFirst){
//         int firstDigit = extractFirstDigit(line, words, isFirst);
//         int secondDigit = extractFirstDigit(
//             reverse(line),
//             words.stream().map(Main2::reverse).collect(Collectors.toList()),
//             isFirst);
//         return firstDigit * 10 + secondDigit;
//     }

//     private static int extractFirstDigit(String line , List<String> words, boolean isFirst){
//         String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
//         // System.out.println(line);
//         TreeMap<Integer, Integer> map = new TreeMap<>();

//         for(int i = 0; i< words.size(); i++){
//             int indexWord = line.indexOf(words.get(i));
//             int indexNum = line.indexOf(nums[i]);   
//             if(indexWord != -1){
//                 map.put(indexWord, i);
//                 // line = line.replaceAll(words[i], String.valueOf(i));
//             }
//             if(indexNum != -1){
//                 map.put(indexNum, i);
//             }                  
//         }
//         return map.get(map.firstKey());
//     }

// }