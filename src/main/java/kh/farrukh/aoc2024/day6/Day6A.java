package kh.farrukh.aoc2024.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kotlin.Pair;

public class Day6A {

  public static void main(String[] args) {
    var map = new ArrayList<List<PositionType>>();

    var sc = new Scanner(System.in);

    var guard = new Pair<>(0, 0);

    while (sc.hasNextLine()) {
      var line = sc.nextLine();
      if (line.isEmpty()) {
        break;
      }

      var row = new ArrayList<PositionType>();
      char[] charArray = line.toCharArray();
      for (int i = 0; i < charArray.length; i++) {
        var c = charArray[i];
        switch (c) {
          case '.' -> row.add(PositionType.FREE);
          case '#' -> row.add(PositionType.OBSTACLE);
          case '^' -> {
            row.add(PositionType.FREE);
            guard = new Pair<>(map.size(), i);
          }
          default -> throw new IllegalArgumentException("Unexpected character: " + c);
        }
      }
      map.add(row);
    }

    var direction = Direction.UP;
    var isOut = false;

    while (!isOut) {
      var row = guard.getFirst();
      var col = guard.getSecond();

      map.get(guard.getFirst()).set(guard.getSecond(), PositionType.PASSED);

      switch (direction) {
        case UP -> row -= 1;
        case DOWN -> row += 1;
        case LEFT -> col -= 1;
        case RIGHT -> col += 1;
      }

      if (row < 0 || row >= map.size() || col < 0 || col >= map.get(0).size()) {
        isOut = true;
        break;
      }

      var newPos = map.get(row).get(col);
      if (newPos == PositionType.OBSTACLE) {
        direction = switch (direction) {
          case UP -> Direction.RIGHT;
          case RIGHT -> Direction.DOWN;
          case DOWN -> Direction.LEFT;
          case LEFT -> Direction.UP;
        };
      } else {
        guard = new Pair<>(row, col);
      }

    }

    System.out.println(guard);
    map.forEach(System.out::println);

    var passedIndicesCount = map
        .stream()
        .flatMap(List::stream)
        .filter(p -> p == PositionType.PASSED)
        .count();

    System.out.println(passedIndicesCount);
  }

  public enum PositionType {
    FREE,
    OBSTACLE,
    PASSED;

    @Override
    public String toString() {
      return switch (this) {
        case FREE -> ".";
        case OBSTACLE -> "#";
        case PASSED -> "X";
      };
    }
  }

  public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }

}
