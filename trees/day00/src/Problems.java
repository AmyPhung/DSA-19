import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        // sort
        // add median to tree
        // split array into left and right
        // call add median function again on left and right sides
        // base case: array length is 1


        BinarySearchTree<Integer> output = new BinarySearchTree<>();
        Collections.sort(values);

        addMedian(values, output, 0, values.size());
        return output;
    }

    private static void addMedian(List<Integer> values, BinarySearchTree<Integer> tree, int lo, int hi) {
        if (hi == lo) { // Base case
            return;
        } else {
            int median_idx = (lo + hi)/2;  // Divide
            tree.add(values.get(median_idx));
            addMedian(values, tree, lo, median_idx); // Conquer
            addMedian(values, tree, median_idx+1, hi);
        }
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
