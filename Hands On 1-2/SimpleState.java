import java.util.List;

public class SimpleState implements State {
    private String name;

    public SimpleState(String name) {
        this.name = name;
    }

    @Override
    public List<Action> getActions() {
        // Definir acciones posibles desde este estado
        if (name.equals("A")) {
            return List.of(new SimpleAction("A to B"), new SimpleAction("A to C"));
        } else if (name.equals("B")) {
            return List.of(new SimpleAction("B to D"));
        } else if (name.equals("C")) {
            return List.of(new SimpleAction("C to E"));
        } else if (name.equals("D")) {
            return List.of(new SimpleAction("D to F"));
        } else if (name.equals("E")) {
            return List.of(new SimpleAction("E to F"));
        }
        return List.of();
    }

    @Override
    public State getResult(Action action) {
        // Definir el estado resultante de aplicar una acción
        SimpleAction simpleAction = (SimpleAction) action;
        if (simpleAction.getName().equals("A to B")) {
            return new SimpleState("B");
        } else if (simpleAction.getName().equals("A to C")) {
            return new SimpleState("C");
        } else if (simpleAction.getName().equals("B to D")) {
            return new SimpleState("D");
        } else if (simpleAction.getName().equals("C to E")) {
            return new SimpleState("E");
        } else if (simpleAction.getName().equals("D to F")) {
            return new SimpleState("F");
        } else if (simpleAction.getName().equals("E to F")) {
            return new SimpleState("F");
        }
        return this;
    }

    @Override
    public boolean isGoal() {
        // Definir cuándo un estado es el objetivo
        return name.equals("F");
    }

    @Override
    public String toString() {
        return name;
    }
}