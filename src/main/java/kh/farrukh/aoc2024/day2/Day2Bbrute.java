package kh.farrukh.aoc2024.day2;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2Bbrute {

  public static void main(String[] args) {
    var sc = new Scanner(System.in);

    List<List<Integer>> reports = new ArrayList<>();

    while (sc.hasNextLine()) {
      var line = sc.nextLine();
      if (line.isEmpty()) {
        break;
      }
      var report = new ArrayList<Integer>();
      for (var s : line.split(" ")) {
        report.add(Integer.parseInt(s));
      }
      reports.add(report);
    }

    var monotonous = 0;

    for (var report : reports) {
      var size = report.size();
      var variants = new ArrayList<List<Integer>>();
      for (int i = 0; i < size; i++) {
        var variant = new ArrayList<Integer>();
        for (int j = 0; j < size; j++) {
          if (i == j) {
            continue;
          }
          variant.add(report.get(j));
        }
        variants.add(variant);
      }

      for (var variant : variants) {
        if (isValidVariant(variant)) {
          monotonous++;
          break;
        }
      }
    }

    System.out.println(monotonous);
  }

  private static boolean isValidVariant(
      List<Integer> variant
  ) {
    var isIncreasing = variant.get(1) > variant.get(0);

    var firstIsValid = isValid(variant.get(0), variant.get(1), isIncreasing);
    if (!firstIsValid) {
      return false;
    }

    for (int i = 1; i < variant.size() - 1; i++) {
      var current = variant.get(i);
      var next = variant.get(i + 1);

      var isValid = isValid(current, next, isIncreasing);
      if (!isValid) {
        return false;
      }
    }

    return true;
  }

  private static boolean isValid(
      Integer current,
      Integer next,
      boolean isIncreasing
  ) {
    if (isIncreasing && next < current) {
      return false;
    }

    if (!isIncreasing && next > current) {
      return false;
    }

    var diff = abs(current - next);
    return diff >= 1 && diff <= 3;
  }

}

