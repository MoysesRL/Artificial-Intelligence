public class Laberinto {
    private boolean[][] grid;

    public Laberinto(boolean[][] grid) {
        this.grid = grid;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && !grid[x][y];
    }
}