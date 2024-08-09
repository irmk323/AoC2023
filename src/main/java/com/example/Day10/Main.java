package com.example.Day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/Day10/line.text";
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = fileContent.split("\n");
        char[][] matrix = new char[arr.length][arr[0].length()];
        int[] start = new int[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length(); j++) {
                matrix[i][j] = arr[i].charAt(j);
                if (arr[i].charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        int maxDistance = bfs(matrix, start[0], start[1]);
        System.out.println(maxDistance);
    }

    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private static int bfs(char[][] matrix, int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] distances = new int[matrix.length][matrix[0].length];
        for (int[] row : distances) Arrays.fill(row, -1);

        queue.offer(new int[]{startRow, startCol});
        distances[startRow][startCol] = 0;

        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isInRange(matrix, newRow, newCol) && distances[newRow][newCol] == -1
                        && isValidMove(matrix, row, col, newRow, newCol)) {
                    distances[newRow][newCol] = distances[row][col] + 1;
                    maxDistance = Math.max(maxDistance, distances[newRow][newCol]);
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return maxDistance;
    }

    private static boolean isInRange(char[][] matrix, int row, int col) {
        return row >= 0 && col >= 0 && col < matrix[0].length && row < matrix.length;
    }

    private static boolean isValidMove(char[][] maze, int row, int col, int newRow, int newCol) {
        char current = maze[row][col];
        char next = maze[newRow][newCol];

        return canConnect(current, newRow - row, newCol - col) && canConnect(next, row - newRow, col - newCol);
    }

    private static boolean canConnect(char pipe, int dx, int dy) {
        switch (pipe) {
            case '|': return dx != 0 && dy == 0;
            case '-': return dx == 0 && dy != 0;
            case 'L': return (dx == -1 && dy == 0) || (dx == 0 && dy == 1);
            case 'J': return (dx == -1 && dy == 0) || (dx == 0 && dy == -1);
            case '7': return (dx == 1 && dy == 0) || (dx == 0 && dy == -1);
            case 'F': return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
            case 'S': return true;  // 'S' can connect to any direction
            default: return false;
        }
    }
}