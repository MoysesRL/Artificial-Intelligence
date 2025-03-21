import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class SearchAlgorithm {
    protected Frontier frontier;
    protected Set<State> explored = new HashSet<>();
    protected Problem problem;

    public SearchAlgorithm(Problem problem, Frontier frontier) {
        this.problem = problem;
        this.frontier = frontier;
        Node initialNode = new Node(problem.getInitialState(), null, null, 0);
        frontier.add(initialNode);
    }

    public List<Action> search() {
        while (!frontier.isEmpty()) {
            Node node = frontier.remove();
            if (problem.isGoal(node.getState())) {
                return solution(node);
            }
            explored.add(node.getState());
            for (Action action : problem.getActions(node.getState())) {
                State nextState = node.getState().getResult(action);
                if (!explored.contains(nextState)) {
                    Node child = new Node(nextState, node, action, node.getDepth() + 1);
                    frontier.add(child);
                }
            }
        }
        return null;
    }

    private List<Action> solution(Node node) {
        LinkedList<Action> actions = new LinkedList<>();
        while (node.getParent() != null) {
            actions.addFirst(node.getAction());
            node = node.getParent();
        }
        return actions;
    }
}