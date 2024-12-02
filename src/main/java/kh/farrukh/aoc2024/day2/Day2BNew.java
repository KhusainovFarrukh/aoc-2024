package kh.farrukh.aoc2024.day2;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2BNew {

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

    var mons = new ArrayList<List<Integer>>();
    var nonMons = new ArrayList<List<Integer>>();
    var monotonous = 0;
    var nonMonotonous = 0;

    for (var report : reports) {
      var isMono = true;
      var hasOneIncorrect = false;
      var isPrevIncorrect = false;
      var isIncreasing = report.get(1) > report.get(0);

      var firstIsValid = isValid(report.get(0), report.get(1), isIncreasing);
      if (!firstIsValid) {
        hasOneIncorrect = true;
        isPrevIncorrect = true;
        isIncreasing = report.get(2) > report.get(0);
      }

      for (int i = 1; i < report.size() - 1; i++) {
        var current = report.get(i);
        if (isPrevIncorrect) {
          current = report.get(i - 1);
        }
        var next = report.get(i + 1);

        var isValid = isValid(current, next, isIncreasing);
        if (!isValid) {
          if (hasOneIncorrect) {
            isMono = false;
            break;
          } else {
            hasOneIncorrect = true;
            isPrevIncorrect = true;
//            i++;
//            if (i + 2 != report.size()) {
//              isIncreasing = report.get(i + 2) > report.get(i);
//            }
          }
        } else {
          isPrevIncorrect = false;
        }
      }

      if (!isMono) {
        isMono = true;
        isIncreasing = report.get(2) > report.get(1);

        for (int i = 1; i < report.size() - 1; i++) {
          var current = report.get(i);
          var next = report.get(i + 1);

          var isValid = isValid(current, next, isIncreasing);
          if (!isValid) {
            isMono = false;
            break;
          }
        }
      }

      if (isMono) {
        monotonous++;
        if (hasOneIncorrect) {
          mons.add(report);
        }
      } else {
        nonMonotonous++;
        nonMons.add(report);
      }
    }

    System.out.println(monotonous);

//    mons.forEach(System.out::println);
//    nonMons.forEach(System.out::println);

  }

  private static boolean isValid(
      Integer current,
      Integer next,
      Boolean isIncreasing
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
