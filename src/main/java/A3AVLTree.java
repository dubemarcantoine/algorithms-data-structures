import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class A3AVLTree<E extends Comparable<E>> implements Tree<E> {

    /**
     * The number of elements in the tree
     */
    private int size;

    /**
     * The root element of the tree
     */
    private Node<E> root;

    public A3AVLTree() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public void add(E e) {

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
        this.size--;
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new BinaryTreeInorderIterator<>(this.root);
    }

    public int height() {
        if (this.root == null) {
            return 0;
        }
        return this.heightRec(this.root);
    }

    private int heightRec(Node<E> node) {
        if (node == null) {
            return -1;
        }

        return Math.max(this.heightRec(node.getLeft()), this.heightRec(node.getRight())) + 1;
    }

    @Override
    public int size() {
        return this.size;
    }

}
