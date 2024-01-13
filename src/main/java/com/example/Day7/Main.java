package com.example.Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day7/line.text";
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = fileContent.split("\n");
        Stream<String> stringStream = Arrays.stream(arr);
        Long res1 = part1(stringStream);
        System.out.println(res1);
    }

    private static final List<Character> cards = List.of('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');
    public static Long part1(Stream<String> inputStream) {
        List<String[]> list = inputStream.map(s -> s.split(" "))
                // if compareHands < 0, sorted as hand1 ,hand2
                // if compareHands > 0   hand2, hand1
                .sorted((handl1, handl2) -> compareHands(handl1[0], handl2[0]))
                .collect(Collectors.toList());

        return LongStream.range(0, list.size())
                .map(index -> (index + 1) * Long.parseLong(list.get((int) index)[1]))
                .sum();
    }

    private static Map<Character, Integer> getCardFrequencyMap(String hand) {
        return hand.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)));
    }

    private static int getHandType(String hand) {
        return getCardFrequencyMap(hand).values().stream().mapToInt(value -> value * value).sum();
    }
    // value -> value * value ... This process makes the number of occurrences of a card have a greater 
    // impact on the evaluation of the type of card in the hand


    private static int compareHands(String hand1, String hand2 ){
        int type1 = getHandType(hand1);
        int type2 = getHandType(hand2);

        // if it's minus, type 1 is earlier, 
        // if it's plus, type 1 is later.
        if(type1 != type2){
            return type1 - type2;
        }else{
            for(int i = 0; i< hand1.length(); i++){
                int firstCardPosition = cards.indexOf(hand1.charAt(i));
                int secondCardPosition = cards.indexOf(hand2.charAt(i));

                // T55J5 and QQQJA are both three of a kind. 
                // QQQJA has a stronger first card, so it gets rank 5 and T55J5 gets rank 4.
                // based on the order of "cards", 
                // T is 8, Q is 10 so 8 - 10 = -2 , T55J5 is placed earlier position
                if(firstCardPosition != secondCardPosition){
                    return  firstCardPosition - secondCardPosition;
                }
            }
        }
        return 0;
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
