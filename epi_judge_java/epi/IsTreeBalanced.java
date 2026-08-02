package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  static class Balanced {
    boolean balanced;
    int height;

    Balanced(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return checkBalanced(tree).balanced;
  }

  private static Balanced checkBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new Balanced(true, 0);
    }
    Balanced leftBalance = checkBalanced(tree.left);
    Balanced rightBalance = checkBalanced(tree.right);
    int diff = Math.abs(leftBalance.height - rightBalance.height);
    if (!leftBalance.balanced || !rightBalance.balanced || diff > 1) {
      return new Balanced(false, Math.max(leftBalance.height, rightBalance.height));
    }

    return new Balanced(true, Math.max(leftBalance.height, rightBalance.height) + 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
