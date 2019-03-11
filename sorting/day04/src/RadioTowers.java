import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RadioTowers {
    static class Tower {
        double x, y;
        Tower(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double getDist(Tower t1, Tower t2) {
        double xDiff = t1.x - t2.x;
        double yDiff = t1.y - t2.y;
        System.out.println(Math.sqrt(xDiff * xDiff + yDiff * yDiff));
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    private static boolean isWithin(Tower t1, Tower t2, int dist) {
        return getDist(t1, t2) <= dist;
    }

    // Strip contains a list of Towers sorted by x-coordinate, whose y-coordinates all fall in a 2-mile strip
    // Return true if no two towers are within 1 mile
    public static boolean checkStrip(List<Tower> strip) {
        // TODO
        for (int i=0; i<strip.size(); i++) {
            for (int j=i+1; j<i+8; j++) {
                if (j<strip.size() && isWithin(strip.get(i), strip.get(j), 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Return true if no two towers are within distance 1 of each other

    // TOTAL RUNTIME: O(N log N)
    public static boolean validTowers(List<Tower> Lx, List<Tower> Ly) {
        assert Lx.size() == Ly.size();
        // TODO
        if (Ly.size() == 0){
            return true;
        }

        // Base Case - O(N)
        if (Ly.get(Ly.size()-1).y - Ly.get(0).y <= 2) {
            if (checkStrip(Lx) == false) {
                return false;
            }
        } else {
            // Divide - O(1)
            List<Tower> l_Ly = Ly.subList(0, Ly.size()/2);
            List<Tower> r_Ly = Ly.subList(Ly.size()/2 + 1, Ly.size()-1);

            List<Tower> l_Lx = getLx(l_Ly);
            List<Tower> r_Lx = getLx(r_Ly);

            // Conquer - O(log N)
            if (!validTowers(l_Lx, l_Ly) || !validTowers(r_Lx, r_Ly)) {
                return false;
            }

            // Combine - O(N) worst case (should be ~ O(1))
            int mid_i = Ly.size()/2;
            int low_i = Ly.size()/2;
            int hi_i  = Ly.size()/2;

            while (low_i >= 0 && Ly.get(mid_i).y-Ly.get(low_i).y<1){
                if (low_i == 0) {
                    break;
                }
                low_i--;
            }
            while (hi_i <= Ly.size()-1 && Ly.get(hi_i).y-Ly.get(mid_i).y<1){
                // Why does this not work... hi_i should be too big right now
//                if (hi_i == Ly.size()-1){
//                    break;
//                }
                hi_i++;
            }


            List<Tower> mid_strip = Ly.subList(low_i, hi_i);
            if (!checkStrip(mid_strip)) {
                return false;
            }
        }
        return true;
    }

    private static List<RadioTowers.Tower> getLx(List<RadioTowers.Tower> towers) {
        List<RadioTowers.Tower> Lx = new ArrayList<>(towers);
        Collections.sort(Lx, Comparator.comparingDouble(o -> o.x));
        return Lx;
    }
}
