import java.util.*;


public interface Tree<E> {

    /**
     * Adds the specified element to this tree (duplicates are allowed)
     *
     * @param e element to add
     */
    void add(E e);

    /**
     * Adds all of the elements in the specified collection to this tree.
     * (duplicates are allowed)
     *
     * @param c Collection containing the elements
     */
    void addAll(Collection<? extends E> c);

    /**
     * Removes the specified element from this set if it is present.
     * (if more than one were present, removes only the first one)
     *
     * @param e object to remove
     * @return true if this tree contained the element
     */
    boolean remove(E e);

    /**
     * Returns an iterator over the elements in this tree in ascending order
     *
     * @return the iterator described above
     */
    Iterator<E> iterator();

    /**
     * Returns the height of the tree.
     * For an empty tree should return -1.
     * For a tree of one node should return 0
     *
     * @return height of the tree
     */
    int height();

    /**
     * Returns the number of elements in the tree.
     *
     * @return number of elements
     */
    int size();
}
