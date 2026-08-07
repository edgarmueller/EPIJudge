package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class IntervalAdd {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Interval interval = (Interval)o;

      if (left != interval.left) {
        return false;
      }
      return right == interval.right;
    }

    @Override
    public String toString() {
      return "[" + left + ", " + right + "]";
    }
  }

  @EpiTest(testDataFile = "interval_add.tsv")
  public static List<Interval> addInterval(List<Interval> disjointIntervals,
                                           Interval newInterval) {
    List<Interval> newIntervals = new ArrayList<>();
    disjointIntervals.add(newInterval);
    disjointIntervals.sort(Comparator.comparingInt(iv -> iv.left));
    Interval prevInterval = disjointIntervals.getFirst();

    for (Interval iv : disjointIntervals.subList(1, disjointIntervals.size())) {
      if (overlaps(prevInterval, iv)) {
        prevInterval = merge(prevInterval, iv);
      } else {
        newIntervals.add(prevInterval);
        prevInterval = iv;
      }
    }
    newIntervals.add(prevInterval);
    return newIntervals;
  }

  private static Interval merge(Interval iv1, Interval iv2) {
    return new Interval(Math.min(iv1.left, iv2.left), Math.max(iv1.right, iv2.right));
  }

  private static boolean overlaps(Interval iv1, Interval iv2) {
    return !(iv1.right < iv2.left) && !(iv2.right < iv1.left);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntervalAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
