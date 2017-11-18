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

        if (this.right == null && this.left == null) {
            this.setDepth(1);
        } else if (this.right == null){
            this.setDepth(this.left.depth + 1);
        } else if(this.left == null) {
            this.setDepth(this.right.depth + 1);
        } else {
            this.setDepth(Math.max(left.getDepth(), right.getDepth()) + 1);
        }
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
}