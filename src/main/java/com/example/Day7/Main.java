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
        Stream<String> stringStream1 = Arrays.stream(arr);
        Stream<String> stringStream2 = Arrays.stream(arr);
        Long part1 = solution(stringStream1, 1);
        Long part2 = solution(stringStream2, 2);
        System.out.println("part1: " +part1);
        System.out.println("part2: " +part2);
    }

    private static final List<Character> cardsPart1 = List.of('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');
    private static final List<Character> cardsPart2 = List.of('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A');
    public static Long solution(Stream<String> inputStream, int part) {
        List<String[]> list = inputStream.map(s -> s.split(" "))
                // if compareHands < 0, sorted as hand1 ,hand2
                // if compareHands > 0   hand2, hand1
                .sorted((handl1, handl2) -> compareHands(handl1[0], handl2[0], part))
                .collect(Collectors.toList());

        return LongStream.range(0, list.size())
                .map(index -> (index + 1) * Long.parseLong(list.get((int) index)[1]))
                .sum();
    }


    private static Map<Character, Integer> getCardFrequencyMap(String hand, int part) {
        Map<Character, Integer> frequencyMap  = hand.chars()
                .mapToObj(c -> (char) c) 
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)));
        if(part == 2){
            int jCount = frequencyMap.getOrDefault('J', 0);
            frequencyMap.remove('J');
            char maxFreqChar = frequencyMap.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(hand.charAt(0)) ;

                frequencyMap.put(maxFreqChar, frequencyMap.getOrDefault(maxFreqChar, 0) + jCount);
        }
        return frequencyMap;
    }

    private static int getHandType(String hand, int part) {
        return getCardFrequencyMap(hand, part).values().stream().mapToInt(value -> value * value).sum();
    }
    // value -> value * value ... This process makes the number of occurrences of a card have a greater 
    // impact on the evaluation of the type of card in the hand


    private static int compareHands(String hand1, String hand2, int part ){
        int type1 = getHandType(hand1, part);
        int type2 = getHandType(hand2, part);
        List<Character> cards = new ArrayList<>();
        // if it's minus, type 1 is earlier, 
        // if it's plus, type 1 is later.
        if(type1 != type2){
            return type1 - type2;
        }else{
            if(part == 1){
                cards = cardsPart1;
            }else{
                cards = cardsPart2;
            }
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


}
