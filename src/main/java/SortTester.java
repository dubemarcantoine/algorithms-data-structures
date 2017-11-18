import java.util.Arrays;
import java.util.Collections;

public class SortTester {

    public static void main(String[] args) {
        Tree<Integer> tree = new A3BSTree<>();
        Integer a[] = new Integer[100000];

        for (int i = 0; i < a.length; i++) a[i] = i;

        Collections.shuffle(Arrays.asList(a));

        TreeSort.sort(tree, a);
        //TreeSort.sort(a);
    }

}
