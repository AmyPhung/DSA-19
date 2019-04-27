import java.util.HashMap;

public class LongestIncreasingSubsequence {

    // Runtime: TODO
    // Space: TODO
    public static int LIS(int[] A) {
        // TODO
        HashMap<Integer,Integer> DP = new HashMap<>();

        int max_length = 0;
        for (int i=A.length-1; i>=0; i--) {
            int count_from_index = LISHelper(A, i, DP);
            if (count_from_index > max_length) {
                max_length = count_from_index;
            }
        }

        return max_length;
    }

    private static int findNextLargest(int[] A, int i, HashMap<Integer,Integer> DP) {
        int max_idx = -1;
        int max_val = 0;
        for (int idx=i+1; idx<A.length; idx++) {
            if (A[idx] > A[i]) {
                int next_val = LISHelper(A, idx, DP);
                if (next_val > max_val) {
                    max_idx = idx;
                    max_val = next_val;
                }
            }
        }
        return max_idx;
    }

    private static int LISHelper(int[] A, int i,  HashMap<Integer,Integer> DP) {
        if (DP.containsKey(i)) {
            return DP.get(i);
        } else if (i == A.length - 1) {
            return 1; // Return 1 for the last element in subsequence
        } else {
            int next = findNextLargest(A, i, DP);

            if (next == -1) {
                DP.put(i, 1);
                return 1; // Return 1 if there is no higher element
            } else {
                int next_value = LISHelper(A, next, DP);
                DP.put(i, next_value + 1);
                return next_value + 1;
            }
        }
    }
}