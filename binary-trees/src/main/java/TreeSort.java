import java.util.Iterator;

public class TreeSort {

    /**
     * Sorts an array using TreeSort with a balanced BST implementation
     *
     * @param a an array to sort
     */
    public static <E extends Comparable<E>> void sort(E[] a) {
        Tree<E> tree = new A3AVLTree<>();
        sort(tree, a);
    }

    /**
     * Sorts an array using TreeSort with a specified BST
     *
     * @param tree tree to use
     * @param a    an array to sort
     */
    public static <E> void sort(Tree<E> tree, E[] a) {
        for (E element : a) {
            tree.add(element);
        }

        int index = 0;
        Iterator<E> it = tree.iterator();
        while (it.hasNext()) {
            a[index] = it.next();
            index++;
        }
    }
}
