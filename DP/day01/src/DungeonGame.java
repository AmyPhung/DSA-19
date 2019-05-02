public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        // TODO: Your code here
        // DP is the minimum health needed to reach goal from that particular cell
        int[][] DP = new int [map.length][map[0].length];
        initDP(DP); // Set all values to -1

        // Last cell is just the min points from the last
        DP[map.length-1][map[0].length-1] = computeMinHealth(1, map[map.length-1][map[0].length-1]);

        // Populate edges of map
        for (int i=map.length-2; i>=0; i--) {
            DP[i][map[0].length-1] = computeMinHealth(DP[i+1][map[0].length-1], map[i][map[0].length-1]);
        }
        for (int j=map[0].length-2; j>=0; j--) {
            DP[map.length-1][j] = computeMinHealth(DP[map.length-1][j+1], map[map.length-1][j]);
        }

        // Fill in rest of map
        for (int i=map.length-2; i>=0; i--) {
            for (int j=map[0].length-2; j>=0; j--) {
                int up = computeMinHealth(DP[i+1][j], map[i][j]);
                int left = computeMinHealth(DP[i][j+1], map[i][j]);

                DP[i][j] = Math.min(up,left);
            }
        }


        return DP[0][0];
    }

    private static int computeMinHealth(int prev_min, int new_val){
        int new_min = 0;

        // Unchanged if new cell is 0
        if (new_val == 0) {
            new_min = prev_min;
        }
        //
        else if (new_val < 0) {
            new_min = prev_min + Math.abs(new_val);
        }
        //
        else { // new_val is > 0
            new_min = prev_min - Math.abs(new_val);
        }

        return Math.max(new_min, 1); // Min health cannot be less than 1
    }

    private static void initDP(int[][] dp) {
        // Sets all values in DP map to -1 to mark as "empty"
        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
    }
}
