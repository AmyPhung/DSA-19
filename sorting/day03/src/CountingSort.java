public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime:
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        // O(N)
        int k = max(A);

        int[] counts = new int[k+1];

        // O(N)
        for (int e : A) {
            counts[e]++;
        }
        int i=0;

        // O(N + k)
        for (int j=0;j<k+1;j++){
            while (counts[j]>0) {
                A[i]=j;
                counts[j]--;
                i++;
            }
        }
    }

    static int max(int[] A) {
        int max = 0;
        for (int i : A) {
            if (i>max) {
                max=i;
            }
        }
        return max;
    }

}
