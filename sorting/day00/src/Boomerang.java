import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        // TODO
        Map<int[], Map> pts = new HashMap<>();
        for (int i=0; i<points.length; i++) {
            Map<Double, Integer> distances = new HashMap<>();
            for (int j=0; j<points.length; j++){
                if (i!=j){
                    double d = dist(points[i], points[j]);
                    System.out.println(d);
                    int n = distances.getOrDefault(d, 0);
                    n++;

                    System.out.println(n);


                    distances.put(d,n);
                }
            }
            pts.put(points[i],distances);
        }
        int num_boomerangs = 0;

        Set<int[]> pts_set = pts.keySet();

        for (int[] p : pts_set){
            Set<Double> dist_set = pts.get(p).keySet();
            for (double d : dist_set) {
                int n = (int) pts.get(p).get(d);
//                System.out.println(n);
                num_boomerangs += n*(n-1);
            }
        }

        return num_boomerangs;
    }

    private static double dist(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p2[0]-p1[0],2) + Math.pow(p2[1]-p1[1],2));
    }
}

