
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(N log N)
     * Worst-case runtime: O(N log N)
     * Average-case runtime: O(N log N)
     *
     * Space-complexity: O(N log N)
     */
    @Override
    public int[] sort(int[] array) {
        System.out.println(array.length);
        if (array.length <= 1) return array;

        int l_len = (int) Math.ceil((double) array.length / 2.0);
        int r_len = (int) Math.floor((double) array.length / 2.0);

        int[] left = new int[l_len];
        int[] right = new int[r_len];

        System.arraycopy(array, 0, left, 0, l_len);
        System.arraycopy(array, l_len, right, 0, r_len);

        int[] l_sorted = sort(left);
        int[] r_sorted = sort(right);

        return merge(l_sorted, r_sorted);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int i = 0;
        int j = 0;

        int[] result = new int[a.length + b.length];

        for (int k=0; k<a.length + b.length; k++) {
            if (i >= a.length) {
                result[k] = b[j];
                j++;
            } else if(j >= b.length) {
                result[k] = a[i];
                i++;
            }
            else if (a[i] < b[j]) {
                result[k] = a[i];
                i++;
            } else {
                result[k] = b[j];
                j++;
            }
        }
        return result;
    }

}
