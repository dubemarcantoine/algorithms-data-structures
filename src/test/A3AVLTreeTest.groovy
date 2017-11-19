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
}
