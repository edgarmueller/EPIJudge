package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    int lo = 0, hi = A.size() - 1;
    int foundAt = -1;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      int element = A.get(mid);
      if (element < k) {
        lo = mid + 1;
      } else {
        if (element == k) {
          foundAt = mid;
        }
        hi = mid - 1;
      }
    }
    return foundAt;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
