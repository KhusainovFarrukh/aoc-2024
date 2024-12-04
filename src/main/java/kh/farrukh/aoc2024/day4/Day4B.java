package kh.farrukh.aoc2024.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4B {

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

    for (int i = 1; i < lines.size() - 1; i++) {
      var line = lines.get(i);

      for (int j = 1; j < line.size() - 1; j++) {
        var c = line.get(j);

        if (c == 'A') {
          //1. Check left-down to right-up diagonal
          var lowerLine = lines.get(i + 1);
          var upperLine = lines.get(i - 1);
          var lowLeft = lowerLine.get(j - 1);
          var lowRight = lowerLine.get(j + 1);
          var upLeft = upperLine.get(j - 1);
          var upRight = upperLine.get(j + 1);

          var lowLeftToRightUpIsValid = false;
          var lowRightToUpLeftIsValid = false;

          if (lowLeft == 'M' && upRight == 'S') {
            lowLeftToRightUpIsValid = true;
          }
          if (lowLeft == 'S' && upRight == 'M') {
            lowLeftToRightUpIsValid = true;
          }
          if (lowRight == 'M' && upLeft == 'S') {
            lowRightToUpLeftIsValid = true;
          }
          if (lowRight == 'S' && upLeft == 'M') {
            lowRightToUpLeftIsValid = true;
          }

          if (lowLeftToRightUpIsValid && lowRightToUpLeftIsValid) {
            xmasCount++;
          }
        }
      }
    }

    System.out.println(xmasCount);
  }

}
