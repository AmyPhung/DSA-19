/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A* // cost so far
        public int cost; // equal to f-cost in A* // heuristic cost
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = board.manhattan();
        }

        public int computeHeuristic() {
            return cost + moves;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO: Your code here

        return null;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        // TODO: Your code here

        HashSet<Board> visited = new HashSet<>();
        State initial_state = new State(initial, 0, null);

        visited.add(initial_state.board);

        Comparator<State> boardSorter = Comparator.comparing(State::computeHeuristic);
        PriorityQueue<State> queue = new PriorityQueue<>(boardSorter);
        queue.add(initial_state);

        while (!queue.isEmpty()) {
            try
            {
                Thread.sleep(500);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

            System.out.println(queue.size());
            State cur_state = queue.poll();

            for (int i=0; i<3; i++) {
                for (int j=0;j<3;j++) {
                    System.out.print(cur_state.board.tiles[i][j]);
                }
            }
            System.out.println(" ");
            System.out.println("Checking board...");
            System.out.println(cur_state.board.isGoal());

            if (cur_state.board.isGoal()) {
                System.out.println("done!");
                solutionState = cur_state;
                minMoves = cur_state.moves;
                return;
            } else {
                for (Board neighbor : cur_state.board.neighbors()) {
                    State neighbor_state = new State(neighbor, cur_state.moves++, cur_state);

                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor_state);

                        System.out.println("Visited Boards!");
                        for (Board v : visited) {
                            for (int i=0; i<v.size; i++) {
                                for (int j=0; j<v.size; j++) {
                                    System.out.print(v.tiles[i][j]);
                                }

                            }
                            System.out.println(" ");
                        }
                    } else {
                        System.out.println("hello");
                    }
                }

            }
        }

        /*

        Set <state> visited
        initial_state = new state (initial, 0, null)

        add initial_state to visited

        queue = priorityqueue <State>
        queue.put(initial_state.moves + inital_state.cost, inital_state)

        while queue not empty :
            State cur_state = queue.get() (last spot in path - lowest cost)
            if cur_state == goal
                solutionState = cur_state
                min_moves = cur_state.moves
                return;

            for neighbor in cur_state.board.neighbors()
                neighbor_state = new state (neighbor, cur_state.board.moves++, cur_state)
                if neighbor_state not in visited
                    visited.add(neighbor_state)
                    queue.put(neighbor_state.moves + neighbor_state.cost, neighbor_state)
         */


    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        // TODO: Your code here
        return solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        // TODO: Your code here
        // backtrack through solutionState
//        State cur_state = solutionState;
//        int num_moves
//        while (cur_state.prev != null) {
//
//        }
        return null;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
