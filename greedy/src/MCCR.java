import java.util.HashSet;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            // TODO

            HashSet<Integer> visited = new HashSet<>();
            IndexPQ<Edge> queue = new IndexPQ<>(G.numberOfE());

            // Start with 0 total cost
            int total_cost = 0;

            // Start with node 0
            int cur_node = 0;


            while (true) {
                visited.add(cur_node);

                Iterable<Edge> node_edges = G.edges(cur_node);

                for (Edge edge:node_edges) {
                    // neighbor = other end of edge
                    Integer neighbor = edge.other(cur_node);

                    if (visited.contains(neighbor)) {
                        // Don't add visited neighbor edges to queue
                        continue;
                    } else {
                        // Queue unvisited neighbors based on weight
                        if (queue.contains(neighbor)) {
                            // If neighbor is already in queue, update the key (will only update if smaller)
                            queue.changeKey(neighbor, edge);
                        } else {
                            queue.insert(neighbor, edge);
                        }
                    }
                }

                // Remove cyclic edges
                Edge min_edge = queue.delMin();
                while (visited.contains(min_edge.either()) &&
                       visited.contains(min_edge.other(min_edge.either())) &&
                       !queue.isEmpty()) {
                    min_edge = queue.delMin();
                }

                // Add for cost of new road
                total_cost += min_edge.weight();


                cur_node = min_edge.either();
                if (visited.contains(cur_node)) {
                    cur_node = min_edge.other(cur_node);
                }


                if (queue.isEmpty()) {
                    return total_cost;
                }
            }

        }

    }
