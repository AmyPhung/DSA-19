import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    public int size = 0;
    private int space_i = -1;
    private int space_j = -1;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        size = b.length;
        tiles = new int[size][size];

        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++){
                tiles[i][j] = b[i][j];
            }
        }

        n = size*size;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        // TODO: Your code here
        return size;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        // O(N)
        // TODO: Your code here
        int dist = 0;

        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                dist = dist + computeTileManhattan(i,j);
            }
        }
        return dist;
    }
    private int computeTileManhattan(int given_i, int given_j) {
        int k = tiles[given_i][given_j];

//        System.out.println("dist");
//        System.out.println(k);

        int desired_i;
        int desired_j;

        if (k == 0) {
            return 0;
        } else {
            desired_i = Math.floorDiv(k-1, size);
            desired_j = (k-1) % size;
        }

//        System.out.println(desired_i);
//        System.out.println(desired_j);
//        System.out.println(Math.abs(desired_i-given_i) + Math.abs(desired_j-given_j));
        return Math.abs(desired_i-given_i) + Math.abs(desired_j-given_j);
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        // TODO: Your code here
        if (manhattan() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // TODO: Your code here
        int n = countInversions();
        if (n%2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private int countInversions() {
        int inversions = 0;

        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                for (int i2=i; i2<size; i2++) {
                    for (int j2=j; j2<size; j2++) {
                        if (tiles[i2][j2]==0 || tiles[i][j]==0) {
                            continue;
                        }
                        else if (tiles[i2][j2] < tiles[i][j]) {
                            inversions++;
                        }
                    }
                }


//                boardCopy[i*size+j] = tiles[i][j];
            }
        }

//        int idx = 0;
//        while (idx<boardCopy.length) {
//            if (boardCopy[idx] == 0) {
//                if (idx == n-1) { // If zero in correct place
//                    break;
//                }
//
////                swap(boardCopy, idx,  size-1);
//                int temp = boardCopy[idx];
//                boardCopy[idx] = boardCopy[n-1];
//                boardCopy[n-1] = temp;
//                inversions++;
//
//
//            } else if (boardCopy[idx] != idx+1) {
////                swap(boardCopy, idx, boardCopy[idx]-1);
//                int temp = boardCopy[idx];
//                boardCopy[idx] = boardCopy[boardCopy[idx]-1];
//                boardCopy[temp-1] = temp;
//
//                inversions++;
//
//            } else {
//                idx++;
//            }
//
//        }

        return inversions;
    }


    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here
        ArrayList<Board> neighbors = new ArrayList<Board>();

        if (space_i == -1) {
            findSpace();
        }

        // Top
        if (validPoint(space_i-1,space_j)) {
            int i1 = space_i-1;
            int j1 = space_j;
            int i2 = space_i;
            int j2 = space_j;
            Board new_board1 = new Board(tiles);
            int temp = new_board1.tiles[i1][j1];
            new_board1.tiles[i1][j1] = new_board1.tiles[i2][j2];
            new_board1.tiles[i2][j2] = temp;

//            swap(this, space_i-1, space_j, space_i, space_j);
            neighbors.add(new_board1);
        }

        // Bottom
        if (validPoint(space_i+1,space_j)) {
            int i1 = space_i+1;
            int j1 = space_j;
            int i2 = space_i;
            int j2 = space_j;
            Board new_board2 = new Board(tiles);
            int temp = new_board2.tiles[i1][j1];
            new_board2.tiles[i1][j1] = new_board2.tiles[i2][j2];
            new_board2.tiles[i2][j2] = temp;
//            Board new_board2 = swap(this, space_i+1, space_j, space_i, space_j);
            neighbors.add(new_board2);
        }

        // Left
        if (validPoint(space_i,space_j-1)) {
            int i1 = space_i;
            int j1 = space_j-1;
            int i2 = space_i;
            int j2 = space_j;
            Board new_board3 = new Board(tiles);
            int temp = new_board3.tiles[i1][j1];
            new_board3.tiles[i1][j1] = new_board3.tiles[i2][j2];
            new_board3.tiles[i2][j2] = temp;
//            Board new_board3 = swap(this, space_i, space_j-1, space_i, space_j);
            neighbors.add(new_board3);
        }

        // Right
        if (validPoint(space_i,space_j+1)) {
            int i1 = space_i;
            int j1 = space_j+1;
            int i2 = space_i;
            int j2 = space_j;
            Board new_board4 = new Board(tiles);
            int temp = new_board4.tiles[i1][j1];
            new_board4.tiles[i1][j1] = new_board4.tiles[i2][j2];
            new_board4.tiles[i2][j2] = temp;
//            Board new_board3 = swap(this, space_i, space_j-1, space_i, space_j);
            neighbors.add(new_board4);
        }
//
//        System.out.println("original");
//        for (int i=0; i<size; i++) {
//            for (int j = 0; j < size; j++) {
//                System.out.print(tiles[i][j]);
//
//            }
//        }
//        System.out.println("neighbors");
//        for (Board neighbor : neighbors){
//            for (int i=0; i<size; i++) {
//                for (int j=0; j<size; j++) {
//                    System.out.print(neighbor.tiles[i][j]);
//
//                }
//            }
//            System.out.println(" ");
//        }
        return neighbors;
    }

    public Board swap(Board b, int i1, int j1, int i2, int j2) {
        Board new_board = new Board(b.tiles.clone());
//        System.out.println(i1 + " " +  i2 + " " + j1 + " " + j2);


        int temp = new_board.tiles[i1][j1];
        new_board.tiles[i1][j1] = new_board.tiles[i2][j2];
        new_board.tiles[i2][j2] = temp;

//        System.out.println("Swap results");
//        for (int i=0; i<size; i++) {
//            for (int j=0; j<size; j++) {
//                System.out.print(new_board.tiles[i][j]);
//
//            }
//        }
//        System.out.println(" ");

        return new_board;
//        for (int i=0; i<size; i++) {
//            for (int j=0; j<size; j++) {
//                System.out.print(tiles[i][j]);
//            }
//        }
//        System.out.println(" ");
    }

    public int integerRep() {
        int n = 0;
        int current_n = 0;
        for (int i=0; i<tiles.length; i++) {
            for (int j=0; j<tiles.length; j++) {
                n = n + tiles[i][j]*current_n;
                current_n = current_n*10;
            }
        }
        return n;
    }

    private boolean validPoint(int i, int j) {
        if (i<0 || j<0 || i>=size || j >=size) {
            return false;
        } else {
            return true;
        }
    }

    private void findSpace() {
        for (int i=0; i<tiles.length; i++) {
            for (int j=0; j<tiles.length; j++) {
                if (tiles[i][j] == 0) {
                    space_i = i;
                    space_j = j;
                }
            }
        }
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
//        System.out.println("checking equals1");
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != size || y.tiles[0].length != size) {
            return false;
        }
//        System.out.println("checking tile config");
        // Check if the same tile configuration
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int digit = 0;
        int hash = 0;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                hash += Math.pow(10,digit)*tiles[i][j];
                digit++;
            }
        }
        return hash;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
