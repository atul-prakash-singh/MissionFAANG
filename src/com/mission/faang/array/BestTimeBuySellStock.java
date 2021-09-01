package com.mission.faang.array;

public class BestTimeBuySellStock {

    public static void main(String[] args) {
        int[] input = {7, 1, 5, 3, 6, 4};

        // Buy & Sell only once.
        // You must buy before you sell
        System.out.println("Maximum Profit-I is " + findMaximumProfit_I(input));

        // Buy one and sell one share of the stock multiple times.
        // You must sell the stock before you buy again. Multiple transactions simultaneously not allowed.
        System.out.println("Maximum Profit-II is " + findMaximumProfit_II(input));
    }

    private static int findMaximumProfit_I(int[] prices) {
        int maximumProfit = 0;
        int minimumPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minimumPrice) {
                minimumPrice = prices[i];
            } else if (prices[i] - minimumPrice > maximumProfit) {
                maximumProfit = prices[i] - minimumPrice;
            }
        }
        return maximumProfit;
    }

    private static int findMaximumProfit_II(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // As long as price is higher, then do a transaction on following day
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
