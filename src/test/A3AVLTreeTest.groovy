import spock.lang.Specification
import spock.lang.Unroll

class A3AVLTreeTest extends Specification {

    @Unroll
    def "Remove #remove in #values should be #shouldRemove"(int remove, boolean shouldRemove, int newTreeSize,
                                                            int newTreeHeight, ArrayList<Integer> values) {
        Tree<Integer> tree = new A3AVLTree<>()
        tree.addAll(values)

        when:
        boolean isRemoved = tree.remove(remove)

        then:
        isRemoved == shouldRemove

        and:
        tree.size() == newTreeSize

        and:
        tree.height() == newTreeHeight

        where:
        remove | shouldRemove | newTreeSize | newTreeHeight | values
        10     | true         | 7           | 3             | [5, 10, 15, 12, 14, 7, 6, 3]
        111    | false        | 8           | 3             | [5, 10, 15, 12, 14, 7, 6, 3]
        1      | true         | 0           | 0             | [1]
        10     | false        | 0           | 0             | []
    }

    @Unroll
    def "Add #add in #values"(int add, int newTreeSize, int newTreeHeight,
                              ArrayList<Integer> values) {
        Tree<Integer> tree = new A3AVLTree<>()
        tree.addAll(values)

        when:
        tree.add(add)

        then:
        tree.size() == newTreeSize

        and:
        tree.height() == newTreeHeight

        where:
        add | newTreeSize | newTreeHeight | values
        16  | 9           | 3             | [5, 10, 15, 12, 14, 7, 6, 3]
        2   | 4           | 2             | [1, 2, 3]
        -8  | 6           | 2             | [3, 4, 5, 6, 2]
        10  | 1           | 0             | []
    }

    @Unroll
    def "Should return values inorder"(Integer[] values, Integer[] sortedValues) {
        Tree<Integer> tree = new A3AVLTree<>()
        tree.addAll(Arrays.asList(values))

        when:
        Integer[] sortedIndices = new Integer[values.size()]
        int index = 0
        Iterator<Integer> it = tree.iterator()
        while (it.hasNext()) {
            sortedIndices[index] = it.next()
            index++
        }

        then:
        sortedIndices == sortedValues

        where:
        values                                                                     | sortedValues
        [5, 10, 15, 12, 14, 7, 6, 3]                                               | [3, 5, 6, 7, 10, 12, 14, 15]
        [10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, 9, -10] | [-10, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10]
        [1, 2, 3]                                                                  | [1, 2, 3]
        [3, 2, 1]                                                                  | [1, 2, 3]
        []                                                                         | []
    }
}
