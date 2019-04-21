import java.util.*;

public class Solver {
    private State solutionState;
//
    private class State {
        private RubiksCube rubikscube;
        private char move;
        private State prev;

        public State(RubiksCube rubikscube, char move, State prev) {
            this.rubikscube = rubikscube;
            this.move = move;
            this.prev = prev;
        }

    @Override
    public boolean equals(Object s) {
        if (s == this) return true;
        if (s == null) return false;
        if (!(s instanceof State)) return false;
        return ((State) s).rubikscube.equals(this.rubikscube);
    }

    }

    public Solver(RubiksCube initial) {
        solutionState = new State(initial, '0', null);

        HashSet<RubiksCube> visited = new HashSet<>();
        State initial_state = new State(initial, '0', null);

        visited.add(initial);

        Queue<State> queue = new LinkedList<>();
        queue.add(initial_state);

        while(!queue.isEmpty()) {

            State cur_state = queue.poll();

            if (cur_state.rubikscube.isSolved()) {
                solutionState = cur_state;
                return;
            } else {
                Iterable<RubiksCube> neighbors = cur_state.rubikscube.neighbors();
                char[] moves = {'u','U','r','R','f','F'};
                int move_idx = 0;
                for (RubiksCube neighbor : neighbors) {
                    char move = moves[move_idx];
                    State neighbor_state = new State(neighbor, move, cur_state);

                    move_idx++;

                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor_state);

                    } else {

                    }
                }

            }
        }
    }

    public ArrayList<Character> ListSolution() {
        ArrayList<Character> rev_solution = new ArrayList<>();
        State cur_state = solutionState;

        while (cur_state.prev != null) { // while state.prev is not null
            rev_solution.add(cur_state.move); // add at end (going backwards)
            cur_state = cur_state.prev;

        }

        ArrayList<Character> solution = new ArrayList<>();
        for (int i=rev_solution.size()-1; i>=0; i--) {
            solution.add(rev_solution.get(i));
            System.out.println(rev_solution.get(i));
        }
        return solution;
    }
}
