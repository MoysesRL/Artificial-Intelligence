import java.util.List;

public class Problem {
    private State initialState;

    public Problem(State initialState) {
        this.initialState = initialState;
    }

    public State getInitialState() { return initialState; }

    public boolean isGoal(State state) {
        return state.isGoal();
    }

    public List<Action> getActions(State state) {
        return state.getActions();
    }
}