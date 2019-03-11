public class Problems {

    public static int leastSum(int[] A) {
        //TODO
        // sort the array
        // create numbers by using first elements

        reverseCountingSort(A); // O(N) Time

        int num1 = 0; // O(1) Space
        int num2 = 0;

        int i = 0;
        int j = 0;

        if (A.length == 0) {
            return 0;
        }

        while (true) { // O(N) Time
            num1 = num1 + (int) Math.pow(10,i) * A[i+j];
            i++;
            if (i+j >= A.length) {
                break;
            }
            num2 = num2 + (int) Math.pow(10,j) * A[i+j];
            j++;
            if (i+j >= A.length) {
                break;
            }
        }

        return num1 + num2;
    }

    private static void reverseCountingSort(int[] A) {
        int[] counts = new int[10];
        for (int e : A) {
            counts[e]++;
        }
        int i=0;
        for (int j=9;j>=0;j--) {
            while (counts[j] > 0) {
                A[i] = j;
                counts[j]--;
                i++;
            }
        }
    }
}
