public class EditDistance {

    public static int minEditDist(String a, String b) {
        // TODO: Your code here
        int[][] DP = new int[a.length()+1][b.length()+1];

        for (int i=0; i<=a.length(); i++) {
            for (int j=0; j<=b.length(); j++) {
                // If first string is empty, need to insert characters
                if (i==0) {
                    DP[i][j] = j;
                }

                // If second string is empty, need to insert characters
                else if (j==0) {
                    DP[i][j] = i;
                }

                // If last characters are the same, no need to increment - just look at previous
                else if (a.charAt(i-1) == b.charAt(j-1)) {
                    DP[i][j] = DP[i-1][j-1];
                }

                // If different, need to compare possibilities
                else {
                    DP[i][j] = 1 + Math.min(DP[i][j-1],     // Insert
                                   Math.min(DP[i-1][j],     // Delete
                                            DP[i-1][j-1])); // Replace
                }
            }
        }

        return DP[a.length()][b.length()];
    }

}
