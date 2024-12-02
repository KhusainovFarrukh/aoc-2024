package kh.farrukh.aoc2024.day2;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2B {

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
      var isMono = true;
      var hasOneIncorrect = false;
      var isIncreasing = report.get(0) < report.get(1);

      var diffFirst = abs(report.get(0) - report.get(1));

      if (diffFirst < 1 || diffFirst > 3) {
        hasOneIncorrect = true;
        isIncreasing = report.get(1) < report.get(2);
      }

      for (int i = 1; i < report.size() - 1; i++) {
        var currentIncorrect = false;
        if (isIncreasing && report.get(i) > report.get(i + 1)) {
          if (hasOneIncorrect) {
            isMono = false;
            break;
          } else {
            hasOneIncorrect = true;
            currentIncorrect = true;
            if (i + 2 != report.size()) {
              isIncreasing = report.get(i) < report.get(i + 2);
            }
//            if (i == 1) {
//              isIncreasing = false;
//            }
          }
        }
        if (!isIncreasing && report.get(i) < report.get(i + 1)) {
          if (hasOneIncorrect) {
            isMono = false;
            break;
          } else {
            hasOneIncorrect = true;
            currentIncorrect = true;
            if (i + 2 != report.size()) {
              isIncreasing = report.get(i) < report.get(i + 2);
            }
//            if (i == 1) {
//              isIncreasing = true;
//            }
          }
        }
        var diff = abs(report.get(i) - report.get(i + 1));
        if (diff < 1 || diff > 3) {
          if (hasOneIncorrect && !currentIncorrect) {
            isMono = false;
            break;
          } else {
            hasOneIncorrect = true;
          }

        }
      }

      if (isMono) {
        monotonous++;
      }
//      else {
//        hasOneIncorrect = true;
//        var isIncreasingNew = report.get(1) < report.get(2);
//
//        var diffFirstNew = abs(report.get(1) - report.get(2));
//
//        if (diffFirst < 1 || diffFirst > 3) {
//          isMono = false;
//        }
//
//        for (int i = 2; i < report.size() - 1; i++) {
//          if (isIncreasing && report.get(i) > report.get(i + 1)) {
//            isMono = false;
//            break;
//          }
//          if (!isIncreasing && report.get(i) < report.get(i + 1)) {
//            isMono = false;
//            break;
//          }
//          var diff = abs(report.get(i) - report.get(i + 1));
//          if (diff < 1 || diff > 3) {
//            isMono = false;
//            break;
//
//          }
//        }
//
//        if (isMono) {
//          monotonous++;
//        }
//      }
    }

    System.out.println(monotonous);

  }

}
