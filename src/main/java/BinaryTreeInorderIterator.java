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
        // Goes left until it finds a null. Going left will give the smallest value
        while (this.currentNode != null) {
            this.parentNodes.push(this.currentNode);
            this.currentNode = this.currentNode.getLeft();
        }

        // Get the last node in the stack which is our smallest value
        this.currentNode = this.parentNodes.pop();
        E value = this.currentNode.getValue();
        // From the last node, go right
        this.currentNode = this.currentNode.getRight();

        return value;
    }
}