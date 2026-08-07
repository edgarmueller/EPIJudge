package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    if (L1 == null) {
      return L2;
    }

    if (L2 == null) {
      return L1;
    }

    ListNode<Integer> p1 = L1, p2 = L2;
    ListNode<Integer> node = new ListNode<>(0, null);
    ListNode<Integer> dummy = node;

    while (p1 != null && p2 != null) {
      if (p1.data < p2.data) {
        node.next = p1;
        p1 = p1.next;
      } else {
        node.next = p2;
        p2 = p2.next;
      }
      node = node.next;
    }


    while (p1 != null) {
      node.next = p1;
      p1 = p1.next;
      node = node.next;
    }

    while (p2 != null) {
      node.next = p2;
      p2 = p2.next;
      node = node.next;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
