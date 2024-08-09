package com.example.Day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
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
        int enclosedArea = calculateEnclosedArea(matrix, start[0], start[1]);
        System.out.println("Enclosed area: " + enclosedArea);
    }

    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private static int calculateEnclosedArea(char[][] matrix, int startRow, int startCol) {
        boolean[][] isLoop = new boolean[matrix.length][matrix[0].length];
        bfs(matrix, startRow, startCol, isLoop);

        // Replace 'S' with the correct pipe type
        matrix[startRow][startCol] = inferStartPipeType(matrix, startRow, startCol);

        int enclosedArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            int crossings = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (isLoop[i][j]) {
                    if (matrix[i][j] == '|' || matrix[i][j] == 'J' || matrix[i][j] == 'L') {
                        crossings++;
                    }
                } else if (crossings % 2 == 1) {
                    enclosedArea++;
                }
            }
        }

        return enclosedArea;
    }

    private static char inferStartPipeType(char[][] matrix, int row, int col) {
        // if we can go to north
        boolean north = row > 0 && "F|7".indexOf(matrix[row-1][col]) != -1;
         // if we can go to south .. and so on
        boolean south = row < matrix.length-1 && "J|L".indexOf(matrix[row+1][col]) != -1;
        boolean west = col > 0 && "F-L".indexOf(matrix[row][col-1]) != -1;
        boolean east = col < matrix[0].length-1 && "7-J".indexOf(matrix[row][col+1]) != -1;

        if (north && south) return '|';
        if (east && west) return '-';
        if (north && east) return 'L';
        if (north && west) return 'J';
        if (south && west) return '7';
        if (south && east) return 'F';

        return 'S'; // This should never happen if the input is valid
    }

    private static void bfs(char[][] matrix, int startRow, int startCol, boolean[][] isLoop) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        isLoop[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isInRange(matrix, newRow, newCol) && !isLoop[newRow][newCol]
                        && isValidMove(matrix, row, col, newRow, newCol)) {
                    isLoop[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
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