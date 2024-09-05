package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstGraphIterator<N extends Comparable<N>> implements GraphIterator<N> {
    private Graph<N> graph;
    private Queue<N> queue;
    private Set<N> visited;

    public BreadthFirstGraphIterator(Graph<N> graph, N startNode) {
        this.graph = graph;
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
        this.queue.add(startNode);
        this.visited.add(startNode);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public N next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more nodes to traverse.");
        }
        N currentNode = queue.poll();
        List<N> adjacencies = graph.getAdjacentNodes(currentNode);

        for (N adjNode : adjacencies) {
            if (!visited.contains(adjNode)) {
                visited.add(adjNode);
                queue.add(adjNode);
            }
        }
        return currentNode;
    }

    public void traverse() {
        while (hasNext()) {
            System.out.println(next());
        }
    }

}
