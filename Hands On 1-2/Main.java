import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Definir el estado inicial
        State initialState = new SimpleState("A");

        // Crear el problema
        Problem problem = new Problem(initialState);

        // Ejecutar DFS
        System.out.println("Ejecutando DFS...");
        DFS dfs = new DFS(problem);
        List<Action> dfsSolution = dfs.search();
        if (dfsSolution != null) {
            System.out.println("Solución encontrada con DFS:");
            for (Action action : dfsSolution) {
                System.out.println(action);
            }
        } else {
            System.out.println("No se encontró solución con DFS.");
        }

        // Ejecutar BFS
        System.out.println("\nEjecutando BFS...");
        BFS bfs = new BFS(problem);
        List<Action> bfsSolution = bfs.search();
        if (bfsSolution != null) {
            System.out.println("Solución encontrada con BFS:");
            for (Action action : bfsSolution) {
                System.out.println(action);
            }
        } else {
            System.out.println("No se encontró solución con BFS.");
        }
    }
}