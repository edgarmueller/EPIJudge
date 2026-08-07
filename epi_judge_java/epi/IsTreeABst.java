package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return isBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBST(BinaryTreeNode<Integer> tree, Integer min, Integer max) {
    if (tree == null) {
      return true;
    }
    if (tree.getData() >= min && tree.getData() <= max) {
      return isBST(tree.getLeft(), min, tree.getData()) && isBST(tree.getRight(), tree.getData(), max);
    }

    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
