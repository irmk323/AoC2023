// package com.example.Day2;


// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.util.Scanner;

// public class Main2 {
//     private static final int NUM_RED = 12;
//     private static final int NUM_GREEN = 13;
//     private static final int NUM_BLUE = 14;

//     public static void main(String[] args) {
//         int n=1;
//         int sumP1 = 0, sumP2 = 0;

//         // Read file
//         try (Scanner fr = new Scanner(new FileReader("lines.text"))){
//             while (fr.hasNext()){
//                 String line = fr.nextLine();

//                 if (solvePart1(line))
//                     sumP1 += n;

//                 sumP2 += solvePart2(line);
//                 n++;
//             }

//             System.out.format("Part 1: %d%n", sumP1);
//             System.out.format("Part 2: %d%n", sumP2);
//         } catch (FileNotFoundException e){
//             System.err.println(e.getMessage());
//         }
//     }

//     private static boolean solvePart1(String line){
//         Scanner s;
//         line = line.split(":")[1].trim().replace(';', ',');
//         String[] cubes = line.split(", ");

//         for (var c : cubes){
//             s = new Scanner(c);
//             if (c.contains("red")){
//                 int num = s.nextInt();
//                 if (num > NUM_RED) return false;
//             } else if (c.contains("green")) {
//                 int num = s.nextInt();
//                 if (num > NUM_GREEN) return false;
//             } else if (c.contains("blue")){
//                 int num = s.nextInt();
//                 if (num > NUM_BLUE) return false;
//             }
//         }

//         return true;
//     }

//     private static int solvePart2(String line){
//         Scanner s;
//         line = line.split(":")[1].trim().replace(';', ',');
//         String[] cubes = line.split(", ");
//         int maxRed = 0, maxGreen = 0, maxBlue = 0;

//         for (var c : cubes){
//             s = new Scanner(c);
//             if (c.contains("red")){
//                 int num = s.nextInt();
//                 if (num > maxRed)
//                     maxRed = num;
//             } else if (c.contains("green")) {
//                 int num = s.nextInt();
//                 if (num > maxGreen)
//                     maxGreen = num;
//             } else if (c.contains("blue")){
//                 int num = s.nextInt();
//                 if (num > maxBlue)
//                     maxBlue = num;
//             }
//         }

//         return maxRed * maxGreen * maxBlue;
//     }
// }
