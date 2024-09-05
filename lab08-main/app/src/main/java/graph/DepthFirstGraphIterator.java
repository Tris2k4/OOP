package graph;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

public class DepthFirstGraphIterator<N extends Comparable<N>> implements GraphIterator<N> {
    private Graph<N> graph;
    private Stack<N> stack;
    private Set<N> visited;

    public DepthFirstGraphIterator(Graph<N> graph, N startNode) {
        this.graph = graph;
        this.stack = new Stack<>();
        this.visited = new HashSet<>();
        this.stack.add(startNode);
        this.visited.add(startNode);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public N next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more nodes to traverse.");
        }
        N currentNode = stack.peek();
        stack.pop();
        List<N> adjacencies = graph.getAdjacentNodes(currentNode);
        for (N adjNode : adjacencies) {
            if (!visited.contains(adjNode)) {
                stack.push(adjNode);
                visited.add(adjNode);
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
