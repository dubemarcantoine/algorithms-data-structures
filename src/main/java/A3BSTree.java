import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class A3BSTree<E extends Comparable<E>> implements Tree<E> {

    private E[] tree;
    private int size;
    private int height;

    public A3BSTree() {
        this.tree = (E[]) new Object[0];
        this.size = 0;
        this.height = 0;
    }

    @Override
    public void add(E e) {
        if (this.size() == 0) {
            this.tree[0] = e;
        }
    }

    @Override
    public void addAll(Collection<? extends E> c) {
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
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    private int leftIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int rightIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private void increaseCapacity() {
        int newCapacity = (int)Math.pow(2, this.height);
        E[] newTree = (E[]) new Object[newCapacity];

    }
}
