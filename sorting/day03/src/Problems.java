import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // TODO
        int min=-100;
        int max = 100;

        int[] counts = new int[max-min+1];

        // O(N)
        for (int e : A) {
            counts[e + max]++;
        }
        int i=0;

        // O(N + k)
        for (int j=0;j<max-min+1;j++){
            while (counts[j]>0) {
                A[i]=j-max;
                counts[j]--;
                i++;
            }
        }
    }


    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // TODO
        int min= 97; // ascii for a
        int max = 122; // ascii for z

        LinkedList<String>[] L = new LinkedList[max-min+1];
        // O(b)
        for (int i=0; i<max-min+1; i++) {
            L[i] = new LinkedList<>();
        }

        // O(N)
        for (String c : A) {
            // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
            int digit = getNthCharacter(c, n);
            L[digit].add(c);
        }

        // O(N+b)
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            // TODO: Put all numbers in the linked lists into A
            for (String c : list) {
                A[j] = c;
                j++;
            }
        }

    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // TODO
        for (int i=0; i<stringLength; i++) {
            countingSortByCharacter(S,i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
