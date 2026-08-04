package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int numCombinationsForFinalScore(int finalScore,
                               List<Integer> individualPlayScores) {
    int[] combinations =  new  int[finalScore + 1];
    combinations[0] = 1;
    for (int score : individualPlayScores) {
      for (int i = score; i <= finalScore; i++) {
        combinations[i] += combinations[i - score];
      }
    }
    return combinations[finalScore];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
