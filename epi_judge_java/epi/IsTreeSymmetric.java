package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return true;
    }
    return checkSymmetry(tree.left, tree.right);
  }

  private static boolean checkSymmetry(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> otherSide) {
    if (tree == null && otherSide == null) {
      return true;
    }
    if (tree != null && otherSide == null || tree == null && otherSide != null) {
      return false;
    }
    if (tree.data.equals(otherSide.data)) {
      return checkSymmetry(tree.right, otherSide.left) && checkSymmetry(otherSide.right, tree.left);
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
