import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// use this class if you are designing your own Rubik's cube implementation
public class RubiksCube {
    static int size = 2;
    int[][][] cube = new int[size][size][size]; // Cubie locations in i,j,k axes
    int[][][] orientation = new int[size][size][size]; // 1 is along i, 2 is along j, 3 is along k

    // initialize a solved rubiks cube
    public RubiksCube() {
        // TODO
        int cubie = 1;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                for (int k=0; k<size; k++) {
                    cube[i][j][k] = cubie;
                    orientation[i][j][k] = 3;
                    cubie++;
                }
            }
        }
    }


    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        // TODO
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                for (int k=0; k<size; k++) {
                    cube[i][j][k] = r.cube[i][j][k];
                    orientation[i][j][k] = r.orientation[i][j][k];
                }
            }
        }
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
//        System.out.println("running equals");

        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        // TODO

//        System.out.println("comparing cubes");
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                for (int k=0; k<size; k++) {
                    if (this.cube[i][j][k] != other.cube[i][j][k] ||
                        this.orientation[i][j][k] != other.orientation[i][j][k]) {
                        return false;
                    }
                }
            }
        }
//        System.out.println("returning true");
        return true;
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        // TODO
        int hash = 0;
        int digit = 0;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                for (int k=0; k<size; k++) {
                    hash += cube[i][j][k]*Math.pow(10, digit);
                    digit++;
                }
            }
        }
        return hash;
    }

    public boolean isSolved() {
        // TODO
        RubiksCube solved = new RubiksCube();
//        System.out.println("Is Solved Result:");
//        System.out.println(this.equals(solved));
        return this.equals(solved);

    }


    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        // TODO

        RubiksCube rotated_cube = new RubiksCube(this);

        if (c == 'u') {
            rotated_cube.cube[0][0][1] = cube[0][1][1];
            rotated_cube.cube[0][1][1] = cube[1][1][1];
            rotated_cube.cube[1][1][1] = cube[1][0][1];
            rotated_cube.cube[1][0][1] = cube[0][0][1];

            rotated_cube.orientation[0][0][1] = rotationLookup(c,orientation[0][1][1]);
            rotated_cube.orientation[0][1][1] = rotationLookup(c,orientation[1][1][1]);
            rotated_cube.orientation[1][1][1] = rotationLookup(c,orientation[1][0][1]);
            rotated_cube.orientation[1][0][1] = rotationLookup(c,orientation[0][0][1]);

        } else if (c == 'U') {
            rotated_cube.cube[0][0][1] = cube[1][0][1];
            rotated_cube.cube[0][1][1] = cube[0][0][1];
            rotated_cube.cube[1][1][1] = cube[0][1][1];
            rotated_cube.cube[1][0][1] = cube[1][1][1];

            rotated_cube.orientation[0][0][1] = rotationLookup(c,orientation[1][0][1]);
            rotated_cube.orientation[0][1][1] = rotationLookup(c,orientation[0][0][1]);
            rotated_cube.orientation[1][1][1] = rotationLookup(c,orientation[0][1][1]);
            rotated_cube.orientation[1][0][1] = rotationLookup(c,orientation[1][1][1]);
        } else if (c == 'r') {
            rotated_cube.cube[1][0][0] = cube[1][0][1];
            rotated_cube.cube[1][0][1] = cube[1][1][1];
            rotated_cube.cube[1][1][1] = cube[1][1][0];
            rotated_cube.cube[1][1][0] = cube[1][0][0];

            rotated_cube.orientation[1][0][0] = rotationLookup(c,orientation[1][0][1]);
            rotated_cube.orientation[1][0][1] = rotationLookup(c,orientation[1][1][1]);
            rotated_cube.orientation[1][1][1] = rotationLookup(c,orientation[1][1][0]);
            rotated_cube.orientation[1][1][0] = rotationLookup(c,orientation[1][0][0]);
        } else if (c == 'R') {
            rotated_cube.cube[1][0][0] = cube[1][1][0];
            rotated_cube.cube[1][0][1] = cube[1][0][0];
            rotated_cube.cube[1][1][1] = cube[1][0][1];
            rotated_cube.cube[1][1][0] = cube[1][1][1];

            rotated_cube.orientation[1][0][0] = rotationLookup(c,orientation[1][1][0]);
            rotated_cube.orientation[1][0][1] = rotationLookup(c,orientation[1][0][0]);
            rotated_cube.orientation[1][1][1] = rotationLookup(c,orientation[1][0][1]);
            rotated_cube.orientation[1][1][0] = rotationLookup(c,orientation[1][1][1]);
        } else if (c == 'f') {
            rotated_cube.cube[0][0][0] = cube[0][0][1];
            rotated_cube.cube[0][0][1] = cube[1][0][1];
            rotated_cube.cube[1][0][1] = cube[1][0][0];
            rotated_cube.cube[1][0][0] = cube[0][0][0];

            rotated_cube.orientation[0][0][0] = rotationLookup(c,orientation[0][0][1]);
            rotated_cube.orientation[0][0][1] = rotationLookup(c,orientation[1][0][1]);
            rotated_cube.orientation[1][0][1] = rotationLookup(c,orientation[1][0][0]);
            rotated_cube.orientation[1][0][0] = rotationLookup(c,orientation[0][0][0]);
        } else if (c == 'F') {
            rotated_cube.cube[0][0][0] = cube[1][0][0];
            rotated_cube.cube[0][0][1] = cube[0][0][0];
            rotated_cube.cube[1][0][1] = cube[0][0][1];
            rotated_cube.cube[1][0][0] = cube[1][0][1];

            rotated_cube.orientation[0][0][0] = rotationLookup(c,orientation[1][0][0]);
            rotated_cube.orientation[0][0][1] = rotationLookup(c,orientation[0][0][0]);
            rotated_cube.orientation[1][0][1] = rotationLookup(c,orientation[0][0][1]);
            rotated_cube.orientation[1][0][0] = rotationLookup(c,orientation[1][0][1]);
        }

        return rotated_cube;
    }

    private int rotationLookup(char c, int o) {
        if (c == 'u') {
            if (o == 2) return 1;
            else if (o == 1) return -2;
            else if (o == -2) return -1;
            else if (o == -1) return 2;
            else return o; // Orientation unchanged for cubes pointed upwards/downwards
        } else if (c == 'U') {
            if (o == 1) return 2;
            else if (o == -2) return 1;
            else if (o == -1) return -2;
            else if (o == 2) return -1;
            else return o; // Orientation unchanged for cubes pointed upwards/downwards
        } else if (c == 'r') {
            if (o == 3) return 2;
            else if (o == 2) return -3;
            else if (o == -3) return -2;
            else if (o == -2) return 3;
            else return o; // Orientation unchanged for cubes pointed right/left
        } else if (c == 'R') {
            if (o == 2) return 3;
            else if (o == -3) return 2;
            else if (o == -2) return -3;
            else if (o == 3) return -2;
            else return o; // Orientation unchanged for cubes pointed right/left
        } else if (c == 'f') {
            if (o == 3) return 1;
            else if (o == 1) return -3;
            else if (o == -3) return -1;
            else if (o == -1) return 3;
            else return o; // Orientation unchanged for cubes pointed front/back
        } else if (c == 'F') {
            if (o == 1) return 3;
            else if (o == -3) return 1;
            else if (o == -1) return -3;
            else if (o == 3) return -1;
            else return o; // Orientation unchanged for cubes pointed front/back
        } else {
            return o; // Should never reach this - won't change cube
        }
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r = r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        // TODO
        return new ArrayList<>();
    }

}



