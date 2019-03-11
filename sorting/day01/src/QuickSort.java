import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: N log N
     * Worst-case runtime: N^2
     * Average-case runtime: N log N
     *
     * Space-complexity: log N (for recursion stack)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO: Sort the array. Make sure you avoid the O(N^2) runtime worst-case

        System.out.println(array.length);

        if (array.length <= 1) {
            return array;
        } else {
            shuffleArray(array);
            quickSort(array, 0,array.length-1);
            return array;
        }
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
//      Attempt at recursion:
//
//        int pivot_idx = 0;
//        if (lo + 1 == hi) {
//            if (array[lo] > array[hi]){
//                swap(array, lo, hi);
//                pivot_idx = hi;
//            }
//            else {
//                pivot_idx = lo;
//            }
//        } else if (array[lo] > array[hi]) {
//            rotate(array, lo, lo+1, hi);
//            pivot_idx = partition(array, lo+1, hi);
//
//        } else {
//            pivot_idx = partition(array, lo, hi-1);
//        }
//        return pivot_idx;

        int pivot_idx = lo;

        int i = lo + 1;

        for (int j = lo + 1; j <= hi; j++) {
            if (array[j] < array[pivot_idx]) {
                swap(array, j, i);
                i++;
            }
        }

        swap(array, lo, i-1);
        return i-1;

    }

    private void rotate(int[] array, int idx1, int idx2, int idx3) {
        swap(array, idx1, idx2);
        swap(array, idx1, idx3);
    }

}
