import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Used to print a binary tree in a formatted way.
 * Inspired from https://stackoverflow.com/a/4973083
 */
public class BTreePrinter {

    static String printNode(Node root) {
        int maxLevel = maxLevel(root);
        StringBuffer stringBuffer = new StringBuffer();
        return printNodeInternal(Collections.singletonList(root), 1, maxLevel, stringBuffer).toString();
    }

    private static StringBuffer printNodeInternal(List<Node> nodes, int level, int maxLevel, StringBuffer stringBuffer) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) {
            return stringBuffer;
        }

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces, stringBuffer);

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                stringBuffer.append(node.getValue());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                stringBuffer.append(" ");
            }

            printWhitespaces(betweenSpaces, stringBuffer);
        }
        stringBuffer.append("\n");

        for (int i = 1; i <= endgeLines; i++) {
            for (Node node : nodes) {
                printWhitespaces(firstSpaces - i, stringBuffer);
                if (node == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1, stringBuffer);
                    continue;
                }

                if (node.getLeft() != null) {
                    stringBuffer.append("/");
                } else {
                    printWhitespaces(1, stringBuffer);
                }

                printWhitespaces(i + i - 1, stringBuffer);

                if (node.getRight() != null) {
                    stringBuffer.append("\\");
                } else {
                    printWhitespaces(1, stringBuffer);
                }

                printWhitespaces(endgeLines + endgeLines - i, stringBuffer);
            }

            stringBuffer.append("\n");
        }

        return printNodeInternal(newNodes, level + 1, maxLevel, stringBuffer);
    }

    private static void printWhitespaces(int count, StringBuffer stringBuffer) {
        for (int i = 0; i < count; i++) {
            stringBuffer.append(" ");
        }
    }

    private static int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.getLeft()), maxLevel(node.getRight())) + 1;
    }

    private static boolean isAllElementsNull(List list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}