package com.mission.faang.array;

public class MoveZeros {

    public static void main(String[] args) {
        int[] input = {0, 1, 0, 3, 12, 5, 0, 2};
        moveZeroes(input);
    }

    private static void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int nonZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != nonZeroIndex) {
                    nums[nonZeroIndex] = nums[i];
                    nums[i] = 0;
                }
                nonZeroIndex++;
            }
        }
    }
}
