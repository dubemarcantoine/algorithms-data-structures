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
        this.size += c.size();
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int height() {
        if (this.root == null) {
            return 0;
        }
        return this.heightRec(this.root);
    }

    @Override
    public int size() {
        return this.size;
    }

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
    }

    private int heightRec(Node<E> node) {
        if (node == null) {
            return -1;
        }

        return Math.max(this.heightRec(node.getLeft()), this.heightRec(node.getRight())) + 1;
    }

    public static void main(String[] args) {
        Tree<Long> t = new A3BSTree<>();
        System.out.println(t.height());
        t.add(10l);
        System.out.println(t.height());
        t.add(10l);
        System.out.println(t.height());
        t.add(1l);
        System.out.println(t.height());
        t.add(4l);
        System.out.println(t.height());
        t.add(3l);
        System.out.println(t.height());
        t.add(11l);
        t.add(0l);
        t.add(-1l);
        t.add(-2l);
        System.out.println(t.height());
    }
}
