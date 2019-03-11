import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        PriorityQueue<Integer> min = minPQ();
        PriorityQueue<Integer> max = maxPQ();

        int extra = 0;

        double[] runningMedian = new double[inputStream.length];
        // TODO
        for (int i=0; i<inputStream.length; i++) {

            int input = inputStream[i];

            if (i%2 == 0) { // Start with even
                if (max.isEmpty()) { // initial case
                    extra = input;
                } else if ( input <= max.peek()) {
                    extra = max.poll();
                    max.offer(input);
                } else if (input >= min.peek()) {
                    extra = min.poll();
                    min.offer(input);
                } else {
                    extra = input;
                }

                runningMedian[i] = extra;

            } else { // Start with odd
                if (input > runningMedian[i-1]) {
                    max.offer(extra);
                    min.offer(input);
                } else {
                    min.offer(extra);
                    max.offer(input);
                }
                runningMedian[i] = ((float) max.peek() + (float) min.peek())/2.0;
            }
        }

        return runningMedian;
    }

}
