import java.util.Iterator;
import java.util.Stack;

public class BinaryTreeInorderIterator<E extends Comparable<E>> implements Iterator<E> {

    private Node<E> currentNode;
    private Stack<Node<E>> parentNodes;

    BinaryTreeInorderIterator(Node<E> node) {
        this.parentNodes = new Stack<>();
        this.currentNode = node;
    }

    @Override
    public boolean hasNext() {
        return this.currentNode != null || !this.parentNodes.empty();
    }

    @Override
    public E next() {
        this.stackSmallest(this.currentNode);

        // Get the last node in the stack which is our smallest value
        this.currentNode = this.parentNodes.pop();
        // From the last node, go right
        E value = this.currentNode.getValue();
        this.currentNode = this.currentNode.getRight();

        return value;
    }

    /**
     * Goes left until it finds a null. Going left will give the smallest value
     * @param node
     */
    private void stackSmallest(Node<E> node) {
        if (node != null) {
            this.parentNodes.push(node);
            this.stackSmallest(node.getLeft());
        }
    }
}