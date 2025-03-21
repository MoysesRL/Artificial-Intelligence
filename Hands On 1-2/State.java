import java.util.List;

public interface State {
    List<Action> getActions();
    State getResult(Action action);
    boolean isGoal();
}