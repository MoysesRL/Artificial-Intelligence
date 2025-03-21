import java.util.ArrayList;
import java.util.List;

public class LaberintoState implements State {
    private int x;
    private int y;
    private Laberinto maze;

    public LaberintoState(int x, int y, Laberinto maze) {
        this.x = x;
        this.y = y;
        this.maze = maze;
    }

    @Override
    public List<State> getSuccessors() {
        List<State> successors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (maze.isValid(newX, newY)) {
                successors.add(new LaberintoState(newX, newY, maze));
            }
        }
        return successors;
    }

    @Override
    public int getHeuristic(State goal) {
        LaberintoState g = (LaberintoState) goal;
        return Math.abs(x - g.x) + Math.abs(y - g.y);
    }

    @Override
    public boolean isGoal(State goal) {
        return equals(goal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LaberintoState)) return false;
        LaberintoState other = (LaberintoState) obj;
        return x == other.x && y == other.y;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
}