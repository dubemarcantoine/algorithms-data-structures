import java.util.Collection;
import java.util.Iterator;

public class A3BSTree<E extends Comparable<E>> implements Tree<E> {

    /**
     * The number of elements in the tree
     */
    private int size;

    /**
     * The root element of the tree
     */
    private Node<E> root;

    /**
     * Used internally to know if a node was removed or not
     */
    private boolean removed;

    public A3BSTree() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public void add(E e) {
        // Base case
        if (this.size() == 0) {
            this.root = new Node<>(e);
        } else {
            this.addRec(e, this.root);
        }
        this.size++;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        for (E e : c) {
            this.add(e);
        }
    }

    @Override
    public boolean remove(E e) {
        Node<E> removedNode = this.removeRec(e, this.root);
        if (!this.removed) {
            return false;
        }
        this.removed = false;
        this.root = removedNode;
        this.size--;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new BinaryTreeInorderIterator<>(this.root);
    }

    @Override
    public int height() {
        if (this.root == null) {
            return 0;
        }
        return this.root.getDepth() - 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return BTreePrinter.printNode(this.root);
    }

    /**
     * Adds a node recursively
     * @param e
     * @param currentNode
     */
    private void addRec(E e, Node<E> currentNode) {
        if (e.compareTo(currentNode.getValue()) <= 0) {
            if (currentNode.getLeft() != null) {
                this.addRec(e, currentNode.getLeft());
            } else {
                currentNode.setLeft(new Node<>(e));
            }
        } else {
            if (currentNode.getRight() != null) {
                this.addRec(e, currentNode.getRight());
            } else {
                currentNode.setRight(new Node<>(e));
            }
        }

        // Update the height
        currentNode.updateDepth();
    }

    /**
     * Removes nodes recursively
     * @param e
     * @param currentNode
     * @return
     */
    private Node<E> removeRec(E e, Node<E> currentNode) {
        if (currentNode == null) {
            return null;
        }
        if (e.compareTo(currentNode.getValue()) < 0) {
            currentNode.setLeft(this.removeRec(e, currentNode.getLeft()));
        } else if (e.compareTo(currentNode.getValue()) > 0) {
            currentNode.setRight(this.removeRec(e, currentNode.getRight()));
        } else {
            this.removed = true;
            // One children nodes
            if (currentNode.getLeft() == null) {
                return currentNode.getRight();
            } else if (currentNode.getRight() == null) {
                return currentNode.getLeft();
            }
            // 2 children nodes
            currentNode.setValue(this.getMinValueRightSubTree(currentNode.getRight()));
            currentNode.setRight(this.removeRec(currentNode.getValue(), currentNode.getRight()));
        }

        // Update the height
        currentNode.updateDepth();

        return currentNode;
    }

    /**
     * Gets the min value of a node
     * @param node
     * @return
     */
    private E getMinValueRightSubTree(Node<E> node) {
        if (node.getLeft() == null) {
            return node.getValue();
        }
        return this.getMinValueRightSubTree(node.getLeft());
    }
}
