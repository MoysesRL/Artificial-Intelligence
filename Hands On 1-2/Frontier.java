public interface Frontier {
    void add(Node node);
    Node remove();
    boolean isEmpty();
}