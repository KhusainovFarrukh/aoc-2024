package kh.farrukh.aoc2024.day2;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2A {

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
      var isIncreasing = report.get(0) < report.get(1);

      var diffFirst = abs(report.get(0) - report.get(1));
      if (diffFirst < 1 || diffFirst > 3) {
        isMono = false;
      } else {
        for (int i = 1; i < report.size() - 1; i++) {
          if (isIncreasing && report.get(i) > report.get(i + 1)) {
            isMono = false;
            break;
          }
          if (!isIncreasing && report.get(i) < report.get(i + 1)) {
            isMono = false;
            break;
          }
          var diff = abs(report.get(i) - report.get(i + 1));
          if (diff < 1 || diff > 3) {
            isMono = false;
            break;
          }
        }
      }

      if (isMono) {
        monotonous++;
      }
    }

    System.out.println(monotonous);

  }

}
