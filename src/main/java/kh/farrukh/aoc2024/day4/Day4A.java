package kh.farrukh.aoc2024.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4A {

  public static void main(String[] args) {
    var sc = new Scanner(System.in);

    var lines = new ArrayList<List<Character>>();

    while (sc.hasNextLine()) {
      var line = sc.nextLine();
      if (line.isEmpty()) {
        break;
      }
      var chars = new ArrayList<Character>();
      for (var c : line.toCharArray()) {
        chars.add(c);
      }
      lines.add(chars);
    }

    for (var line : lines) {
      for (var c : line) {
        System.out.print(c);
      }
      System.out.println();
    }

    var xmasCount = 0;

    for (int i = 0; i < lines.size(); i++) {
      var line = lines.get(i);

      for (int j = 0; j < line.size(); j++) {
        var c = line.get(j);
        if (c == 'X') {
          // Check if the next 3 characters are M, A, S
          if (j < line.size() - 3) {
            var c1 = line.get(j + 1);
            var c2 = line.get(j + 2);
            var c3 = line.get(j + 3);
            if (c1 == 'M' && c2 == 'A' && c3 == 'S') {
              xmasCount++;
            }
          }

          // Check if the previous 3 characters are M, A, S
          if (j >= 3) {
            var c4 = line.get(j - 1);
            var c5 = line.get(j - 2);
            var c6 = line.get(j - 3);
            if (c4 == 'M' && c5 == 'A' && c6 == 'S') {
              xmasCount++;
            }
          }

          // Check vertically (to down)
          if (i < lines.size() - 3) {
            var line1 = lines.get(i + 1);
            var line2 = lines.get(i + 2);
            var line3 = lines.get(i + 3);
            if (line1.get(j) == 'M' && line2.get(j) == 'A' && line3.get(j) == 'S') {
              xmasCount++;
            }
          }

          // Check vertically (to up)
          if (i >= 3) {
            var line1 = lines.get(i - 1);
            var line2 = lines.get(i - 2);
            var line3 = lines.get(i - 3);
            if (line1.get(j) == 'M' && line2.get(j) == 'A' && line3.get(j) == 'S') {
              xmasCount++;
            }
          }

          // Check diagonally (to down-right)
          if (i < lines.size() - 3 && j < line.size() - 3) {
            var line1 = lines.get(i + 1);
            var line2 = lines.get(i + 2);
            var line3 = lines.get(i + 3);
            if (line1.get(j + 1) == 'M' && line2.get(j + 2) == 'A' && line3.get(j + 3) == 'S') {
              xmasCount++;
            }
          }

          // Check diagonally (to up-right)
          if (i >= 3 && j < line.size() - 3) {
            var line1 = lines.get(i - 1);
            var line2 = lines.get(i - 2);
            var line3 = lines.get(i - 3);
            if (line1.get(j + 1) == 'M' && line2.get(j + 2) == 'A' && line3.get(j + 3) == 'S') {
              xmasCount++;
            }
          }

          // Check diagonally (to down-left)
          if (i < lines.size() - 3 && j >= 3) {
            var line1 = lines.get(i + 1);
            var line2 = lines.get(i + 2);
            var line3 = lines.get(i + 3);
            if (line1.get(j - 1) == 'M' && line2.get(j - 2) == 'A' && line3.get(j - 3) == 'S') {
              xmasCount++;
            }
          }

          // Check diagonally (to up-left)
          if (i >= 3 && j >= 3) {
            var line1 = lines.get(i - 1);
            var line2 = lines.get(i - 2);
            var line3 = lines.get(i - 3);
            if (line1.get(j - 1) == 'M' && line2.get(j - 2) == 'A' && line3.get(j - 3) == 'S') {
              xmasCount++;
            }
          }
        }
      }
    }

    System.out.println(xmasCount);
  }

}
