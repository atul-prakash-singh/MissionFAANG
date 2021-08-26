package com.mission.faang.array;

public class NumberOfIslands {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        System.out.println("Total number of islands : "+getNumberOfIslands(grid));
    }

    private static int ROWS = 0;
    private static int COLS = 0;
    private static final int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    private static int getNumberOfIslands(int[][] grid) {
        int numberOfIslands = 0;
        ROWS = grid.length;
        if (ROWS > 0) {
            COLS = grid[0].length;
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == 1) {
                    numberOfIslands++;
                    traverse(grid, i, j);
                }
            }
        }
        return numberOfIslands;
    }

    private static void traverse(int[][] grid, int i, int j) {
        grid[i][j] = 2;
        for (int[] direction : directions) {
            int row = i + direction[0];
            int col = j + direction[1];
            if (isSafe(grid, row, col)) {
                traverse(grid, row, col);
            }
        }
    }

    private static boolean isSafe(int[][] grid, int i, int j) {
        return i >= 0 && i < ROWS && j >= 0 && j < COLS && grid[i][j] == 1;
    }
}
