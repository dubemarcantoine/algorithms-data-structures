public class Node<E extends Comparable<E>> {

    private Node<E> left;
    private Node<E> right;
    private E value;

    public Node(E value) {
        this.value = value;
    }

    public Node(Node<E> left, Node<E> right, E value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
