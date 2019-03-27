package divide_and_conquer;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        // TODO
        int start = 0; // where to start looking
        int end = nums.length; // where to stop looking

        while (start != end) { // O(log N worst case)
            int mid = (int) Math.floor((end + start) / 2.0);
            int x = peakOneD(mid, nums);  // O(1)
            if (x == 0) {
                return mid;
            }
            else if (x == -1) {
                end = mid;
            }
            else if (x == 1) {
                start = mid + 1;
            }
        }
        return 0; // should never get here
    }

    public static int[] findTwoDPeak(int[][] nums) {
        // TODO
        int x_start = 0;
        int x_end = nums[0].length;
        int y_start = 0;
        int y_end = nums.length;

        boolean b = true;
        while (x_end != x_start && y_end != y_start) {
            if (b) {
                int x_mid = (int) Math.floor((x_end + x_start) / 2.0);
                int y_max = maxYIndex(x_mid, y_start, y_end, nums);
                int peak = peakX(x_mid, y_max, nums);

                if (peak == 0)
                    return new int[]{y_max, x_mid};
                else if (peak == -1)
                    x_end = x_mid;
                else if (peak == 1)
                    x_start = x_mid + 1;
            } else {
                int y_mid = (int) Math.floor((y_end + y_start) / 2.0);
                int x_max = maxXIndex(y_mid, x_start, x_end, nums);
                int peak = peakY(x_max, y_mid, nums);

                if (peak == 0)
                    return new int[]{y_mid, x_max};
                else if (peak == -1)
                    y_end = y_mid;
                else if (peak == 1)
                    y_start = y_mid + 1;
            }
            b = !b;
        }
        return null;
    }

}
