// package com.example.Day1;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.TreeMap;
// import java.util.List;



// public class Main2 {
//     public static void main(String[] args) {
//         String filePath = "../../../resources/lines.text";
//         int ans = 0;
//         try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//             String line;
//             // Read each line using BufferedReader
//             // 3369eightnine89
//             while ((line = reader.readLine()) != null) {
//                 // System.out.println(line);
//                 int extractedNum = extractDigits(line);
//                 // int extractSecond = extractDigits(line, false);
//                 // int num = (extractFirst*10) + extractSecond;
//                 // System.out.println(line + " : " +  extractFirst + " : " + extractSecond + ":" + num);
//                 ans += extractedNum;
//             }         
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         System.out.println(ans);
//         //  System.out.println(extractDigits("eightwothree", true));
         
//         // System.out.println(extractDigits("3369eight89", false));
        
//     }
//     private static int extractDigits(String line ){
//         String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
//         String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
//         TreeMap<Integer, Integer> map = new TreeMap<>();
//         for(int i = 0; i< words.length; i++){
//             List<Integer> matchingIndices = List.of(
//                 line.indexOf(words[i]),
//                 line.lastIndexOf(words[i]),
//                 line.indexOf(nums[i]),
//                 line.lastIndexOf(nums[i]));
            
//             final int constantI = i;
//             matchingIndices.stream()
//                 .filter(iMatch -> iMatch > -1)
//                 .forEach(iMatch -> map.put(iMatch, constantI));
//         }

//         int firstDigit = map.get(map.firstKey());
//         int lastDigit = map.get(map.lastKey());

//         // int secondDigit = 0;
//         // for(int i  = line.length() -1; i >=0 ; i--){
//         //     if(Character.isDigit(line.charAt(i))){
//         //         secondDigit = Integer.valueOf(line.charAt(i));
//         //     }
//         // }
        
//         return firstDigit * 10 + lastDigit;

//     }

// }