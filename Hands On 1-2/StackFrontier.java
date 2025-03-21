import java.util.Stack;

public class StackFrontier implements Frontier {
    private Stack<Node> stack = new Stack<>();

    @Override
    public void add(Node node) { stack.push(node); }

    @Override
    public Node remove() { return stack.pop(); }

    @Override
    public boolean isEmpty() { return stack.isEmpty(); }
}