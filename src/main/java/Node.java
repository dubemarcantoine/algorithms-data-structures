/**
 * Node class
 * @param <E>
 */
public class Node<E extends Comparable<E>> {

    /**
     * The node to the left
     */
    private Node<E> left;

    /**
     * The node to the right
     */
    private Node<E> right;

    /**
     * The value of the node
     */
    private E value;

    /**
     * The depth of the node
     */
    private int depth;

    public Node(E value) {
        this(null, null, value);
    }

    public Node(Node<E> left, Node<E> right, E value) {
        this.left = left;
        this.right = right;
        this.value = value;

        this.depth = 1;
    }

    /**
     * Returns if the tree is balanced or not.
     * It is balanced if the value returned is bounded by [-1, 1]
     * @return
     */
    public int getBalance() {
        int leftDepth = this.getLeft() != null ? this.getLeft().getDepth() : 0;
        int rightDepth = this.getRight() != null ? this.getRight().getDepth() : 0;
        return leftDepth - rightDepth;
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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void updateDepth() {
        int leftDepth = this.getLeft() != null ? this.getLeft().getDepth() : 0;
        int rightDepth = this.getRight() != null ? this.getRight().getDepth() : 0;
        this.depth = Math.max(leftDepth, rightDepth) + 1;
    }
}