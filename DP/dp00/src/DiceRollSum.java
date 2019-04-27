import java.util.HashMap;

public class DiceRollSum {

    // Runtime: TODO
    // Space: TODO



    public static int diceRollSum(int N) {
        // TODO
        HashMap<Integer, Integer> DP = new HashMap<>();
        return diceRollSumHelper(N, DP);
    }

    private static int diceRollSumHelper(int N, HashMap<Integer,Integer> DP) {
        if (DP.containsKey(N)) {
            return DP.get(N);
        } else if (N < 1) {
            return 1;
        } else if (N <= 6) {
            switch (N) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 4;
                case 4:
                    return 8;
                case 5:
                    return 16;
                case 6:
                    return 32;
            }
        } else {
            int new_value = diceRollSum(N-1) +
                    diceRollSum(N-2) +
                    diceRollSum(N-3) +
                    diceRollSum(N-4) +
                    diceRollSum(N-5) +
                    diceRollSum(N-6);
            DP.put(N, new_value);
            return DP.get(N);
        }
        return 0;
    }

}
