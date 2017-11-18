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

    @Override
    public String toString() {
        return BTreePrinter.printNode(this.root);
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
        return currentNode;
    }

    private E getMinValueRightSubTree(Node<E> node) {
        if (node.getLeft() == null) {
            return node.getValue();
        }
        return this.getMinValueRightSubTree(node.getLeft());
    }

    private int heightRec(Node<E> node) {
        if (node == null) {
            return -1;
        }

        return Math.max(this.heightRec(node.getLeft()), this.heightRec(node.getRight())) + 1;
    }

    public static void main(String[] args) {
        Tree<String> t = new A3BSTree<>();
        t.add("h");
        t.add("a");
        t.add("b");
        t.add("c");
        t.add("d");
        t.add("e");
        t.add("f");
        t.add("g");
        t.add("i");
        t.add("j");
        t.add("k");
        t.add("l");
        t.add("m");
        t.add("n");
        t.add("o");
        t.add("p");
        t.add("q");
        t.add("r");
        t.add("s");
        t.add("t");
        t.add("u");
        t.add("v");
        t.add("w");
        t.add("x");
        t.add("y");
        t.add("z");
//        System.out.println(t.toString());
        Iterator<String> it = t.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
