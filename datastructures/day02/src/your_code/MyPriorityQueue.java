package your_code;

import java.util.ArrayList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private ArrayList<Integer> l;

    public MyPriorityQueue() {
        l = new ArrayList<>();
    }

    public void enqueue(int item) {
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) > item) {
                l.add(i, item);
                return;
            }
        }
        l.add(item);
    }

    public int dequeueMax() {
        return l.remove(l.size() - 1);
    }

}