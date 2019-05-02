public class SplitCoins {

    public static int splitCoins(int[] coins) {
        // TODO
        int sum = computeSum(coins);

        int[][] dp = new int[coins.length][sum];
        initDP(dp); // Sets all values to -1

        return Math.abs(sum - splitCoinsRecurse(coins, dp, 0, 0, sum)*2); // Use recursive function starting from 0,0, return difference
    }

    private static int computeSum(int[] coins) {
        int sum = 0;
        for (int coin:coins) {
            sum += coin;
        }
        return sum;
    }

    private static void initDP(int[][] DP) {
        for (int i=0; i<DP.length; i++) {
            for (int j=0; j<DP[0].length; j++) {
                DP[i][j] = -1;
            }
        }
    }

    private static int splitCoinsRecurse(int[] coins, int[][] DP, int i, int j, int sum) {
        // Base Case - last coin
        if (i == coins.length-1) {
            return j; // Return total sum accrued from prior decisions
        }

        // Refer to saved values - if a value is saved, it can't be -1
        else if (DP[i][j] != -1) {
            return DP[i][j];
        }

        // Use recursion to fill in value if not saved
        else {
            int right = splitCoinsRecurse(coins, DP, i+1, j, sum); // Total left sum (j) unchanged
            int left = splitCoinsRecurse(coins, DP, i+1, j+coins[i], sum); // Total left sum (j) increases by coin value (coins[i])
            int best = 0;

            // Choose whether right or left is better - "better" means closer to median
            if (Math.abs(right-sum/2) < Math.abs(left-sum/2)) {
                best = right;
            } else {
                best = left;
            }

            // Save values
            DP[i][j] = best;

            return best;
        }
    }
}

