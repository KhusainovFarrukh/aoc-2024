package kh.farrukh.aoc2024.day3;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Pair;

public class Day3B {

  public static void main(String[] args) {
    var sc = new Scanner(System.in);

    var sb = new StringBuilder();

    while (sc.hasNextLine()) {
      var line = sc.nextLine();
      if (line.isEmpty()) {
        break;
      }
      sb.append(line);
    }

    var line = sb.toString();

    var indexedParts = new TreeMap<Integer, Pair<Character, String>>();

    String mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
    Pattern mulPattern = Pattern.compile(mulRegex);
    Matcher mulMatcher = mulPattern.matcher(line);

    while (mulMatcher.find()) {
      var aStr = mulMatcher.group(1);
      if (aStr.startsWith("0")) {
        continue;
      }
      var bStr = mulMatcher.group(2);
      if (bStr.startsWith("0")) {
        continue;
      }
      var index = mulMatcher.start();
      indexedParts.put(index, new Pair<>('m',mulMatcher.group(0)));
    }


    String doRegex = "do\\(\\)";
    Pattern doPattern = Pattern.compile(doRegex);
    Matcher doMatcher = doPattern.matcher(line);

    while (doMatcher.find()) {
      var index = doMatcher.start();
      indexedParts.put(index, new Pair<>('e', doMatcher.group(0)));
    }

    String dontRegex = "don't\\(\\)";
    Pattern dontPattern = Pattern.compile(dontRegex);
    Matcher dontMatcher = dontPattern.matcher(line);

    while (dontMatcher.find()) {
      var index = dontMatcher.start();
      indexedParts.put(index, new Pair<>('d', dontMatcher.group(0)));
    }

    for (var entry : indexedParts.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }

    var res = 0;
    var enabled = true;

    for (var entry : indexedParts.entrySet()) {
      var part = entry.getValue();

      var type = part.getFirst();
      var expression = part.getSecond();

      if (type == 'e') {
        enabled = true;
      }

      if (type == 'd') {
        enabled = false;
      }

      if (type == 'm' && enabled) {
        var matcher = mulPattern.matcher(expression);
        while (matcher.find()) {
          var aStr = matcher.group(1);
          if (aStr.startsWith("0")) {
            continue;
          }
          var bStr = matcher.group(2);
          if (bStr.startsWith("0")) {
            continue;
          }
          var a = Integer.parseInt(aStr);
          var b = Integer.parseInt(bStr);
          res += a * b;
        }
      }
    }

    System.out.println(res);
  }

}
