import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class A3AVLTree<E> implements Tree<E> {

    private TreeSet<E> tree;

    public A3AVLTree() {
        tree = new TreeSet<>();
    }

    @Override
    public void add(E e) {
        tree.add(e);
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        tree.addAll(c);
    }

    @Override
    public boolean remove(Object o) {
        return tree.remove(o);
    }

    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
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
