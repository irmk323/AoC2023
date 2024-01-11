package com.example.Day5;
import java.io.*;
import java.util.*;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(":")[1].split("");
        List<int[]> seeds = new ArrayList<>();
        for (int i = 0; i < inputs.length; i += 2) {
            seeds.add(new int[]{Integer.parseInt(inputs[i]), Integer.parseInt(inputs[i]) + Integer.parseInt(inputs[i + 1])});
        }
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            List<int[]> ranges = new ArrayList<>();
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split("");
                ranges.add(new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])});
            }
            List<int[]> newSeeds = new ArrayList<>();
            while (!seeds.isEmpty()) {
                int[] seed = seeds.remove(seeds.size() - 1);
                int s = seed[0], e = seed[1];
                for (int[] range : ranges) {
                    int a = range[0], b = range[1], c = range[2];
                    int os = Math.max(s, b);
                    int oe = Math.min(e, b + c);
                    if (os < oe) {
                        newSeeds.add(new int[]{os - b + a, oe - b + a});
                        if (os > s) {
                            seeds.add(new int[]{s, os});
                        }
                        if (e > oe) {
                            seeds.add(new int[]{oe, e});
                        }
                        break;
                    } else {
                        newSeeds.add(new int[]{s, e});
                    }
                }
            }
            seeds = newSeeds;
        }
        System.out.println(Collections.min(seeds, Comparator.comparingInt(a -> a[0]))[0]);
    }
}
