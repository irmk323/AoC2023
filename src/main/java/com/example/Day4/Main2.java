package com.example.Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main2 {
        public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day4/lines.text";
        int lineCount = 0;

        // count lines
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                lineCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<Integer, Long> ansMap = new HashMap<>();
            int[] countArray = new int[lineCount+1];
            for (int i = 1; i < countArray.length; i++) {
                countArray[i]=1;
            }
            while ((line = reader.readLine()) != null) {

                String[] arr= line.split(" \\| ");
                int givenCardNumber = Integer.valueOf(arr[0].split(": ")[0].split("\\s+")[1]);
                String[] givenCards =  arr[0].split(": ")[1].split("\\s+");
                String[] hitNums = arr[1].split("\\s+");
 
                long count = Arrays.stream(givenCards).filter(Arrays.asList(hitNums)::contains).count();
                ansMap.put(givenCardNumber, ansMap.getOrDefault(givenCardNumber, 0L)+1);
                //refer the commentout below
                for(int j = givenCardNumber +1 ; j <= givenCardNumber + count ; j++){
                    countArray[j] += countArray[givenCardNumber];
                }
            }
            int sum = Arrays.stream(countArray).sum();
            System.out.println(sum);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}


// //
// 1
// 2 2 
// 3 3 3 3
// 4 4 4 4 4 4 4 4
// 5 5     5 5 5 5 5 5 5 5 5 5 5 5 
// 6 

// let's think original card

// 1 2 3 4 5

// Card 1 has four matching numbers, so you win one copy each of the next four cards: cards 2, 3, 4, and 5.

// So here, for the card 1, got 4 count, so add card from i(i=1)+1 until the length of count.
// 1 2 3 4 5
//   2 3 4 5


// Your original card 2 has two matching numbers, so you win one copy each of cards 3 and 4.
// Your copy of card 2 also wins one copy each of cards 3 and 4.

// Means below
// 1 2 3 4 5
//   2 3 4 5
//     3 4    <- original  
//　　　3 4    <- copy of the card

// The number of card of "2" is currently 2. you need to calc twice for duplication card.
// In the array in each segment, it records how many cards there is in the indexies.
// repeat from i+1 to i+count(= means matched numbers count) 
// for each indexies(e,g 3 and 4), add i times (+2 for each 3 and 4 as original and copy generates that 3,4 and 3,4)

// Your four instances of card 3 (one original and three copies) have two matching numbers, so you win four copies each of cards 4 and 5.

// Means below
// 1 2 3 4 5
//   2 3 4 5
//     3 4    
//　　　3 4  
//       4 5
//       4 5
//       4 5
//       4 5

// Your eight instances of card 4 (one original and seven copies) have one matching number, so you win eight copies of card 5.
// and so on..