import java.util.List;

public interface State {
    List<State> getSuccessors();
    int getHeuristic(State goal);
    boolean isGoal(State goal);
}