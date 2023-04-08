import java.util.LinkedList;
import java.util.Queue;

public class source {


    public static class Node {
        int data;
        Node left, right;

        Node(int _val) {
            this.data = _val;
            left = right = null;
        }
    }

    public static class Tree {
        Node root;

        Tree() {
            root = null;
        }


        private int nodesWithoutChildren(Node root) {
            Queue<Node> queue = new LinkedList<Node>();
            Node tmp = root;
            int count = 0;
            queue.add(tmp);
            while (!queue.isEmpty()) {
                tmp = queue.poll();
                if (tmp.left != null) queue.add(tmp.left);
                if (tmp.right != null) queue.add(tmp.right);
                if (tmp.left == null && tmp.right == null) count++;

            }

            return count;
        }


        public int nodesWithoutChildren() {
            return nodesWithoutChildren(root);
        }


        int largestNumberEdges() {
            int result = largestNumberEdgesRec(root);
            return result > 0 ? result - 1 : 0;
        }


        boolean compareTree(Tree tree) {
            if (root == null && tree.root == null) return true;
            if ((root == null && tree != null) || (root != null && tree == null)) return false;


            Queue<Node> queue = new LinkedList<>();
            Queue<Node> queueC = new LinkedList<>();

            Node tmp = root;
            Node tmpC = tree.root;

            queue.add(tmp);
            queueC.add(tmpC);

            while (!queue.isEmpty() && !queueC.isEmpty()) {
                tmp = queue.poll();
                tmpC = queueC.poll();
                if (
                        (tmp != null && tmpC == null) || (tmp == null && tmpC != null) || (tmp.data != tmpC.data) ||
                                (tmp.left != null && tmpC.left == null) || (tmp.left == null && tmpC.left != null) ||
                                (tmp.right != null && tmpC.right == null) || (tmp.right == null && tmpC.right != null)
                ) return false;

                if (tmp.left != null && tmp.left.data != tmpC.left.data) return false;
                if (tmp.right != null && tmp.right.data != tmpC.right.data) return false;


                if (tmp.left != null && tmpC.left != null) {
                    queue.add(tmp.left);
                    queueC.add(tmpC.left);
                }

                if (tmp.right != null && tmpC.right != null) {
                    queue.add(tmp.right);
                    queueC.add(tmpC.right);
                }
            }

            return true;
        }

        private int largestNumberEdgesRec(Node root) {
            if (root == null) return 0;
            else {
                int leftHeight = largestNumberEdgesRec(root.left);
                int rightHeight = largestNumberEdgesRec(root.right);

                if (leftHeight > rightHeight) return (leftHeight + 1);
                else return (rightHeight + 1);
            }
        }

    }


    public static void main(String[] args) {


        System.out.println("Testcases for nodesWithoutChildren()\n");
        //Algorithm - modified BFS which count leafs without children

        Tree tree1 = new Tree();
        tree1.root = new Node(1);

        System.out.println("Number of leafs without children in Tree\n1\nNumber: " + +tree1.nodesWithoutChildren());

        Tree tree2 = new Tree();
        tree2.root = new Node(1);
        tree2.root.left = new Node(2);
        tree2.root.right = new Node(3);

        System.out.println("\nNumber of leafs without children in Tree\n " +
                "   1\n" +
                "   / \\\n" +
                "  2   3" +
                "  \nNumber: " + +tree2.nodesWithoutChildren());

        Tree tree3 = new Tree();
        tree3.root = new Node(1);
        tree3.root.left = new Node(2);
        tree3.root.right = new Node(3);
        tree3.root.right.right = new Node(4);


        System.out.println("\nNumber of leafs without children in Tree\n " +
                "   1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "       \\\n" +
                "        4"
                + "\nNumber: " + tree3.nodesWithoutChildren());


        Tree tree4 = new Tree();

        tree4.root = new Node(5);
        tree4.root.left = new Node(3);
        tree4.root.left.left = new Node(2);
        tree4.root.left.right = new Node(5);
        tree4.root.right = new Node(7);
        tree4.root.right.left = new Node(1);
        tree4.root.right.right = new Node(0);
        tree4.root.right.right.left = new Node(2);
        tree4.root.right.right.right = new Node(8);
        tree4.root.right.right.right.right = new Node(5);

        System.out.println("\nNumber of leafs without children in Tree\n " +
                "       5\n" +
                "       / \\\n" +
                "      3   7\n" +
                "     / \\ / \\\n" +
                "    2  5 1  0\n" +
                "           / \\\n" +
                "          2   8\n" +
                "               \\\n" +
                "                5"
                + "\nNumber: " + tree4.nodesWithoutChildren());


        System.out.println("\n------------------------------------------\nTestcases for largestNumberEdges()\n");
        //Algorithm - recursion algorithm to calculating max height in tree, next we can calculate the largest number of edges


        System.out.println("Largest number of edges in a path from the root node to a leaf node in Tree\n1\nNumber: " + +tree1.largestNumberEdges());


        System.out.println("\nLargest number of edges in a path from the root node to a leaf node in Tree\n " +
                "   1\n" +
                "   / \\\n" +
                "  2   3" +
                "  \nNumber: " + +tree2.largestNumberEdges());


        System.out.println("\nLargest number of edges in a path from the root node to a leaf node in Tree\n " +
                "   1\n" +
                "   / \\\n" +
                "  2   3\n" +
                "       \\\n" +
                "        4"
                + "\nNumber: " + tree3.largestNumberEdges());


        System.out.println("\nLargest number of edges in a path from the root node to a leaf node in Tree\n " +
                "       5\n" +
                "       / \\\n" +
                "      3   7\n" +
                "     / \\ / \\\n" +
                "    2  5 1  0\n" +
                "           / \\\n" +
                "          2   8\n" +
                "               \\\n" +
                "                5"
                + "\nNumber: " + tree4.largestNumberEdges());


        System.out.println("\n------------------------------------------\nTestcases for compareTree(tree)\n");
        //Algorithm - Modified BFS with comparison statementT

        System.out.println("\nTree comparision\n"
                + "Tree 1:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3"
                + "\nTree 2:\nnull\n" + "compareTree(Tree2): " + tree1.compareTree(null));


        System.out.println("\nTree comparision\n"
                + "Tree 1:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3"
                + "\nTree 2:\n" +
                "    1\n" +
                "   / \\\n" +
                "  2   3"
                + "\ncompareTree(Tree2): " + tree1.compareTree(tree1));


        System.out.println("\nTree comparision\n " +
                "Tree 1:\n" +
                "        5\n" +
                "       / \\\n" +
                "      3   7\n" +
                "     / \\ / \\\n" +
                "    2  5 1  0\n" +
                "           / \\\n" +
                "          2   8\n" +
                "               \\\n" +
                "                5" +
                "\nTree 2:\n" +
                "        5\n" +
                "       / \\\n" +
                "      3   7\n" +
                "     / \\ / \\\n" +
                "    2  5 1  0\n" +
                "           / \\\n" +
                "          2   8\n" +
                "               \\\n" +
                "                5"
                + "\ncompareTree(Tree2): " + tree4.compareTree(tree4));


        Tree tree5 = new Tree();

        tree5.root = new Node(5);
        tree5.root.left = new Node(3);
        tree5.root.left.left = new Node(2);
        tree5.root.left.right = new Node(5);
        tree5.root.right = new Node(7);
        tree5.root.right.left = new Node(1);
        tree5.root.right.right = new Node(0);
        tree5.root.left.left.left = new Node(2);
        tree5.root.left.left.right = new Node(8);
        tree5.root.left.right.left = new Node(5);


        System.out.println("\nTree comparision\n " +
                "Tree 1:\n" +
                "        5\n" +
                "       / \\\n" +
                "      3   7\n" +
                "     / \\ / \\\n" +
                "    2  5 1  0\n" +
                "           / \\\n" +
                "          2   8\n" +
                "               \\\n" +
                "                5" +
                "\nTree 2:\n" +
                "        5\n" +
                "       / \\\n" +
                "      3   7\n" +
                "     / \\ / \\\n" +
                "    2  5 1  0\n" +
                "   / \\ /\n" +
                "  2   8 5"
                + "\ncompareTree(Tree2): " + tree4.compareTree(tree5));


        //More testcases

        /*
        Tree tree6 = new Tree();
        tree6.root = new Node(4);
        tree6.root.left = new Node(2);
        tree6.root.right = new Node(6);
        tree6.root.left.left = new Node(1);
        tree6.root.left.right = new Node(3);
        tree6.root.right.left = new Node(5);
        tree6.root.right.right = new Node(7);

        Tree tree7 = new Tree();
        tree7.root = new Node(10);
        tree7.root.left = new Node(5);
        tree7.root.right = new Node(15);
        tree7.root.left.left = new Node(3);
        tree7.root.left.right = new Node(7);
        tree7.root.right.left = new Node(13);
        tree7.root.right.right = new Node(17);


        Tree tree8 = new Tree();
        tree8.root = new Node(8);
        tree8.root.left = new Node(4);
        tree8.root.right = new Node(12);
        tree8.root.left.left = new Node(2);
        tree8.root.left.right = new Node(6);
        tree8.root.right.left = new Node(10);
        tree8.root.right.right = new Node(14);

        Tree tree9 = new Tree();
        tree9.root = new Node(20);
        tree9.root.left = new Node(10);
        tree9.root.right = new Node(30);
        tree9.root.left.left = new Node(5);
        tree9.root.left.right = new Node(15);
        tree9.root.right.left = new Node(25);
        tree9.root.right.right = new Node(35);

        Tree tree10 = new Tree();
        tree10.root = new Node(16);
        tree10.root.left = new Node(8);
        tree10.root.right = new Node(24);
        tree10.root.left.left = new Node(4);



        System.out.println(tree6.largestNumberEdges());
        System.out.println(tree7.largestNumberEdges());
        System.out.println(tree8.largestNumberEdges());
        System.out.println(tree9.largestNumberEdges());
        System.out.println(tree10.largestNumberEdges());


        System.out.println(tree6.nodesWithoutChildren());
        System.out.println(tree7.nodesWithoutChildren());
        System.out.println(tree8.nodesWithoutChildren());
        System.out.println(tree9.nodesWithoutChildren());
        System.out.println(tree10.nodesWithoutChildren());

        System.out.println(tree6.compareTree(tree7));
        System.out.println(tree7.compareTree(tree8));
        System.out.println(tree8.compareTree(tree9));
        System.out.println(tree9.compareTree(tree10));
*/


    }
}
