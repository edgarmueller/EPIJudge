package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class PowerSet {
  @EpiTest(testDataFile = "power_set.tsv")

  public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
    List<List<Integer>> powerSet = new ArrayList<>();
    powerSet.add(new ArrayList<>());
    return generateSubSets(0, inputSet, powerSet);
  }

  private static List<List<Integer>> generateSubSets(int idx, List<Integer> inputSet, List<List<Integer>> powerSet) {
    if (idx == inputSet.size()) {
      return powerSet;
    }
    int size = powerSet.size();
    for (int i = 0; i < size; i++) {
      List<Integer> set = powerSet.get(i);
      List<Integer> copy = new ArrayList<>(set);
      copy.add(inputSet.get(idx));
      powerSet.add(copy);
    }
    return generateSubSets(idx + 1, inputSet, powerSet);
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
            .runFromAnnotations(args, "PowerSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
