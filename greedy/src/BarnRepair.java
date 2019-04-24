import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Gap implements Comparable<Gap>{
    Integer size;

    Gap(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(Gap o) {
        return size.compareTo(o.size);
    }
}

public class BarnRepair {


    public static int solve(int M, int[] occupied) {
        /*
        Returns minimum number of stalls to cover with M panels and cows in occupied

        Takes number of doors needed to cover from first to last cow and removes M-1 gaps for stall cover count
         */

        Arrays.sort(occupied);

        // Create max queue of gap sizes
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        int first = occupied[0];
        int last = occupied[occupied.length-1];
        int prev = first-1;

        for (int i=0; i<occupied.length; i++) {
            int stall = occupied[i];

            // Check for gap - if previous cow is next to current
            if (stall-1 != prev) {
                // Add gap sizes to max queue
                queue.add(stall-1-prev);
            }
            prev = stall;
        }

        int stalls_covered = (last-first) + 1;

        for (int i=2; i<=M; i++) {
            // Remove M-1 largest gaps from stall count
            if (queue.peek() == null) {
                continue;
            } else {
                stalls_covered -= queue.poll();
            }
        }
        return stalls_covered;


    }
}