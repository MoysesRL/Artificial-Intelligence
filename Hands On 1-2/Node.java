public class Node {
    private State state;
    private Node parent;
    private Action action;
    private int depth;

    public Node(State state, Node parent, Action action, int depth) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.depth = depth;
    }

    public State getState() { return state; }
    public Node getParent() { return parent; }
    public Action getAction() { return action; }
    public int getDepth() { return depth; }
}