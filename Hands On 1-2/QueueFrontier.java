import java.util.LinkedList;
import java.util.Queue;

public class QueueFrontier implements Frontier {
    private Queue<Node> queue = new LinkedList<>();

    @Override
    public void add(Node node) { queue.add(node); }

    @Override
    public Node remove() { return queue.poll(); }

    @Override
    public boolean isEmpty() { return queue.isEmpty(); }
}