import java.util.ArrayList;
import java.util.List;

public class VialidadState implements State {
    private String id;
    private int x;
    private int y;
    private List<VialidadState> adjacent;

    public VialidadState(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.adjacent = new ArrayList<>();
    }

    public void addAdjacent(VialidadState state) {
        adjacent.add(state);
    }

    @Override
    public List<State> getSuccessors() {
        return new ArrayList<>(adjacent);
    }

    @Override
    public int getHeuristic(State goal) {
        VialidadState g = (VialidadState) goal;
        return (int) Math.hypot(x - g.x, y - g.y);
    }

    @Override
    public boolean isGoal(State goal) {
        return equals(goal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof VialidadState)) return false;
        VialidadState other = (VialidadState) obj;
        return id.equals(other.id);
    }

    // Getters
    public String getId() { return id; }
    public int getX() { return x; }
    public int getY() { return y; }
}