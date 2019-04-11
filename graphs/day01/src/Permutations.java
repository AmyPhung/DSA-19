import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    private static void permutationsHelper(LinkedList<Integer> current, Set<Integer> unused, List<List<Integer>> output) {
        if (unused.size() == 0) {
            output.add(new LinkedList<>(current));
        }

        for (int n : new LinkedList<>(unused)) {
            current.add(n);
            unused.remove(n);
            permutationsHelper(current, unused, output);
            current.removeLastOccurrence(n);
            unused.add(n);
        }
    }

    public static List<List<Integer>> permutations(List<Integer> A) {
        // TODO

        Set<Integer> unused = new HashSet<>();
        for (int n : A) {
            unused.add(n);
        }
        List<List<Integer>> output = new LinkedList<>();
        permutationsHelper(new LinkedList<>(), unused, output);
        return output;
    }

}
