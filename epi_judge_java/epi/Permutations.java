package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.*;

public class Permutations {
  @EpiTest(testDataFile = "permutations.tsv")

  public static List<List<Integer>> permutations(List<Integer> A) {
    List<List<Integer>> allPerms = new ArrayList<>();
    dfs(allPerms, A, new HashSet<>(), new ArrayList<>());
    return allPerms;
  }

  private static void dfs(List<List<Integer>> allPerms, List<Integer> input, Set<Integer> seen, List<Integer> currPerm) {
    if (currPerm.size() == input.size()) {
      allPerms.add(new ArrayList<>(currPerm));
      return;
    }
    for (int i = 0; i < input.size(); i++) {
        if (seen.contains(i)) {
            continue;
        }
        seen.add(i);
        currPerm.add(input.get(i));
        dfs(allPerms, input, seen, currPerm);
        currPerm.removeLast();
        seen.remove(i);
    }
  }

  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Permutations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
