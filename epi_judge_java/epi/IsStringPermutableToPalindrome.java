package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    boolean hasOdd = false;
    Map<Character, Integer> map = new HashMap<>();
    for  (char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      Integer occurrences = entry.getValue();
      if (occurrences % 2 == 0) {
        continue;
      }
      if (hasOdd) {
        return false;
      }
      hasOdd = true;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
