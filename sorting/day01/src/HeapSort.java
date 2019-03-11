

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int l_idx = leftChild(i);
        int r_idx = rightChild(i);

        // Edge case: no children
        if (l_idx > size-1 && r_idx > size-1) {
            System.out.println("Out of bounds");
        }

        // Edge case: only right child
        else if (l_idx > size-1) {
            if (heap[r_idx] > heap[i]) {
                swap(heap, i, r_idx);
                sink(r_idx);
            }
        }

        // Edge case: only left child
        else if (r_idx > size-1) {
            if (heap[l_idx] > heap[i]) {
                swap(heap, i, l_idx);
                sink(l_idx);
            }
        }

        else {
            // Base case: children are smaller
            if (heap[i] >= heap[l_idx] && heap[i] >= heap[r_idx]) {
                return;
            }

            // Recursive case: one or both the children are larger
            int idx;
            if (heap[l_idx] > heap[r_idx]) {
                idx = l_idx;
            } else {
                idx = r_idx;
            }
            System.out.println("swapping");
            swap(heap, i, idx);
            sink(idx);
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: N log N
     * Worst-case runtime: N log N
     * Average-case runtime: N log N
     *
     * Space-complexity: 1 (log N for recursive case)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            // TODO
            swap(heap,i,0);
            size--;
            sink(0);
        }
        return heap;
    }
}
