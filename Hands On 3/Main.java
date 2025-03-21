import java.util.List;

public class Main {
    public static void main(String[] args) {
        testVialidades();
        testLaberintos();
    }

    private static void testVialidades() {
        VialidadState A = new VialidadState("A", 0, 0);
        VialidadState B = new VialidadState("B", 2, 2);
        VialidadState C = new VialidadState("C", 4, 4);

        A.addAdjacent(B);
        B.addAdjacent(A);
        B.addAdjacent(C);
        C.addAdjacent(B);

        HillClimbing hc = new HillClimbing();
        List<State> path = hc.search(A, C);

        System.out.println("Trayectoria en Vialidades (A -> C):");
        printPath(path);
    }

    private static void testLaberintos() {
        boolean[][] grid = {
                {false, true, false},
                {false, false, false},
                {false, true, false}
        };
        Laberinto maze = new Laberinto(grid);
        LaberintoState inicio = new LaberintoState(0, 0, maze);
        LaberintoState fin = new LaberintoState(2, 2, maze);

        HillClimbing hc = new HillClimbing();
        List<State> path = hc.search(inicio, fin);

        System.out.println("\nTrayectoria en Laberinto (0,0 -> 2,2):");
        printPath(path);
    }

    private static void printPath(List<State> path) {
        if (path == null) {
            System.out.println("No se encontró solución.");
            return;
        }

        for (State state : path) {
            if (state instanceof VialidadState) {
                VialidadState vs = (VialidadState) state;
                System.out.println("Vialidad " + vs.getId() + " (" + vs.getX() + "," + vs.getY() + ")");
            } else if (state instanceof LaberintoState) {
                LaberintoState ls = (LaberintoState) state;
                System.out.println("Celda [" + ls.getX() + "," + ls.getY() + "]");
            }
        }
    }
}