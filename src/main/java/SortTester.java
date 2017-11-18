import java.util.*;

public class SortTester {

    public static void main(String[] args) {
        Map<Integer, ArrayList<Metric>> metrics = new LinkedHashMap<>();

        for (int nthPower=1; nthPower<7; nthPower++) {
            int dataSetSize = (int)Math.pow(10, nthPower);
            Integer dataSet[] = new Integer[dataSetSize];

            metrics.put(dataSetSize, new ArrayList<>());

            // Generate reverse ordered list
            for (int i = dataSet.length - 1; i >= 0; i--) {
                dataSet[dataSet.length - 1 - i] = i;
            }

            // Reverse ordered
            // AVL
            metrics.get(dataSetSize).add(prepareSort(new A3AVLTree<Integer>(), dataSet, "AVL (rev-order)"));
            // BST
            metrics.get(dataSetSize).add(prepareSort(new A3BSTree<Integer>(), dataSet, "BST (rev-order)"));

            // Shuffle the data set
            // The dataSet variable will now have a random order
            Collections.shuffle(Arrays.asList(dataSet));
            // AVL
            metrics.get(dataSetSize).add(prepareSort(new A3AVLTree<Integer>(), dataSet, "AVL"));
            // BST
            metrics.get(dataSetSize).add(prepareSort(new A3BSTree<Integer>(), dataSet, "BST"));
        }

        System.out.println();
    }

    /**
     * Prepares a sort for a tree implementation
     * @param tree
     * @param dataSet
     * @param name
     * @return
     */
    private static <E extends Comparable<E>> Metric prepareSort(Tree<E> tree, E[] dataSet, String name) {
        Metric metric = new Metric(name, dataSet.length);
        // Using Arrays.copyOf ensures that we test with the same values each structure
        E copiedDataSet[] = Arrays.copyOf(dataSet, dataSet.length);
        try {
            // Start the timer
            metric.start();
            TreeSort.sort(tree, copiedDataSet);
        } catch (StackOverflowError e) {
            metric.setError(e.getMessage());
        } finally {
            //Stop the timer
            metric.stop();
        }

        return metric;
    }
}
