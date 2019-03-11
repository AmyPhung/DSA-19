import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        Map<Integer, ArrayList<ArrayList<Integer>>> pairsum = new HashMap<>();

        int total = 0;

        for (int i=0; i<arr.length-1; i++) {
            for (int j=i+1; j<arr.length; j++) {
                int key = arr[i] + arr[j];

                ArrayList<Integer> pt = new ArrayList<>();
                pt.add(arr[i]);
                pt.add(arr[j]);

                ArrayList<ArrayList<Integer>> value;

                if (pairsum.getOrDefault(key,null) == null) {
                    value = new ArrayList<>();
                } else {
                    value = pairsum.get(key);
                }
                value.add(pt);
                pairsum.put(key,value);
            }
        }



        for (int item : arr) {
            if (pairsum.getOrDefault(sum-item, null) != null) {
                ArrayList<ArrayList<Integer>> value = pairsum.get(sum-item);
                for (ArrayList<Integer> pt : value) {
                    if (!pt.contains(item)) {
                        total++;
                    }
                }
            }
        }

        return total/3;
    }
}
