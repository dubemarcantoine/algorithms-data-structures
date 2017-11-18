import java.util.Collection;
import java.util.Iterator;

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
        this.root = this.addRec(e, this.root);
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
        if (removedNode == null) {
            return false;
        }
        this.root = removedNode;
        this.size--;
        return true;
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

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return BTreePrinter.printNode(this.root);
    }

    private Node<E> addRec(E e, Node<E> currentNode) {
        if (currentNode == null) {
            return new Node<>(e);
        }
        if (e.compareTo(currentNode.getValue()) <= 0) {
            currentNode.setLeft(this.addRec(e, currentNode.getLeft()));
        } else {
            currentNode.setRight(this.addRec(e, currentNode.getRight()));
        }

        // Update the height
        currentNode.updateDepth();

        int balance = currentNode.getBalance();
        // Left
        if (balance > 1) {
            // Left
            if (e.compareTo(currentNode.getLeft().getValue()) <= 0) {
                return this.rightRotate(currentNode);
            }
            // Right
            else {
                currentNode.setLeft(this.leftRotate(currentNode.getLeft()));
                return this.rightRotate(currentNode);
            }
        }
        // Right
        else if (balance < -1) {
            // Left
            if (e.compareTo(currentNode.getRight().getValue()) <= 0) {
                currentNode.setRight(this.rightRotate(currentNode.getRight()));
                return this.leftRotate(currentNode);
            }
            // Right
            else {
                return this.leftRotate(currentNode);
            }
        }

        return currentNode;
    }

    private int heightRec(Node<E> node) {
        if (node == null) {
            return -1;
        }

        return Math.max(this.heightRec(node.getLeft()), this.heightRec(node.getRight())) + 1;
    }

    private Node<E> rightRotate(Node<E> a) {
        Node<E> b = a.getLeft();
        Node<E> c = b.getRight();

        b.setRight(a);
        a.setLeft(c);

        a.updateDepth();
        b.updateDepth();

        return b;
    }

    private Node<E> leftRotate(Node<E> c) {
        Node<E> b = c.getRight();
        Node<E> a = b.getLeft();

        b.setLeft(c);
        c.setRight(a);

        c.updateDepth();
        b.updateDepth();

        return b;
    }

    private Node<E> removeRec(E e, Node<E> currentNode) {
        if (currentNode == null) {
            return null;
        }
        if (e.compareTo(currentNode.getValue()) < 0) {
            currentNode.setLeft(this.removeRec(e, currentNode.getLeft()));
        } else if (e.compareTo(currentNode.getValue()) > 0) {
            currentNode.setRight(this.removeRec(e, currentNode.getRight()));
        } else {
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

        int balance = currentNode.getBalance();
        // Left
        if (balance > 1) {
            // Left
            if (currentNode.getLeft() != null && currentNode.getLeft().getBalance() >= 0) {
                return this.rightRotate(currentNode);
            }
            // Right
            else {
                currentNode.setLeft(this.leftRotate(currentNode.getLeft()));
                return this.rightRotate(currentNode);
            }
        }
        // Right
        else if (balance < -1) {
            // Left
            if (currentNode.getRight() != null && currentNode.getRight().getBalance() > 0) {
                currentNode.setRight(this.rightRotate(currentNode.getRight()));
                return this.leftRotate(currentNode);
            }
            // Right
            else {
                return this.leftRotate(currentNode);
            }
        }

        return currentNode;
    }

    private E getMinValueRightSubTree(Node<E> node) {
        if (node.getLeft() == null) {
            return node.getValue();
        }
        return this.getMinValueRightSubTree(node.getLeft());
    }

    public static void main(String[] args) {
        Tree<Long> t = new A3AVLTree<>();
        t.add(10l);
        t.add(11l);
        t.add(12l);
        t.add(14l);
        t.add(5l);
        t.add(50l);
        t.add(500l);
        t.add(501l);
        t.add(502l);
        t.add(13l);
        t.add(503l);
        t.add(504l);
        t.add(505l);
        t.add(506l);
        t.remove(11l);
        System.out.println(t.toString());
    }
}
