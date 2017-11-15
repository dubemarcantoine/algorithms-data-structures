import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class A3BSTree<E> implements Tree<E> {

    private E[] tree;

    public A3BSTree() {
        this.tree = (E[]) new Object[0];
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

}
