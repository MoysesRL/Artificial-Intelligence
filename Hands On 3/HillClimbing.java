import java.util.ArrayList;
import java.util.List;

public class HillClimbing {
    public List<State> search(State initialState, State goalState) {
        State currentState = initialState;
        List<State> path = new ArrayList<>();
        path.add(currentState);

        while (true) {
            if (currentState.isGoal(goalState)) return path;

            List<State> successors = currentState.getSuccessors();
            if (successors.isEmpty()) return null;

            State bestSuccessor = null;
            int bestHeuristic = currentState.getHeuristic(goalState);

            for (State successor : successors) {
                int h = successor.getHeuristic(goalState);
                if (h < bestHeuristic) {
                    bestHeuristic = h;
                    bestSuccessor = successor;
                }
            }

            if (bestSuccessor == null) return null;

            currentState = bestSuccessor;
            path.add(currentState);
        }
    }
}