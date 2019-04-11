import java.util.ArrayList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    private static boolean checkValidity(char[][] board, int rowNum, int colNum, int[] occCol) {
        // Assume no conflicts in rows - will only add to one row at a time
        // Check conflicts with columns
        if (occCol[colNum] == 1) {
            return false;
        }
        // Check conflicts with diagonals
        if (checkDiagonal(board, rowNum, colNum)) {
            return false;
        }
        return true;
    }

    private static void addQueen(char[][] board, int rowNum, int colNum, int[] occCol) {
        occCol[colNum] = 1;
        board[rowNum][colNum] = 'Q';
    }
    private static void removeQueen(char[][] board, int rowNum, int colNum, int[] occCol) {
        occCol[colNum] = 0;
        board[rowNum][colNum] = '.';
    }


    private static void nQueensHelper(char[][] board, int n, List<char[][]> output, int rowNum, int[] occCol) {
        if (rowNum == n) {
            output.add(copyOf(board));
            return;
        }
        for (int colNum = 0; colNum < n; colNum++) {
            if (checkValidity(board, rowNum, colNum, occCol)) {
                addQueen(board, rowNum, colNum, occCol);
                nQueensHelper(board, n, output, rowNum + 1, occCol); // add it to valid row
                removeQueen(board, rowNum, colNum, occCol);
            }
            // else continue on and add queen to a different column
        }
    }

    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        char[][] board = new char[n][n];
        int[] occCol = new int[n];
        for (int i=0; i<board.length; i++) {
            occCol[i] = 0;
            for (int j=0; j<board.length; j++) {
                board[i][j] = '.';
            }
        }

        List<char[][]> answers = new ArrayList<>();
        nQueensHelper(board, n, answers, 0, occCol);
        return answers;
    }

}
