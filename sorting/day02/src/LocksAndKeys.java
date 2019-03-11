public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        customQuickSort(locks, keys, 0, locks.length-1);
        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        return result;
    }

    private static void customQuickSort(char[] a, char[] b, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi, b[hi]);
            partition(b,lo,hi,a[p]);
            customQuickSort(a, b, lo, p-1);
            customQuickSort(a, b, p+1, hi);
        }
    }

    private static int partition(char[] array, int lo, int hi, char pivot) {

        int i = lo;

        for (int j = lo; j < hi; j++) {
            if (array[j] < pivot) {
                swap(array, i++, j);
            } else if (array[j] == pivot) {
                swap(array,hi,j--);
            }
        }
        swap(array, i, hi);
        return i;
    }

}




