package kh.farrukh.aoc2024.day3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3A {

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

    var res = 0;

    String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
    Pattern pattern = Pattern.compile(regex);

    Matcher matcher = pattern.matcher(line);
    var matches = new ArrayList<String>();

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
      matches.add(matcher.group(0));
      res += a * b;
    }

    System.out.println(res);
    System.out.println(matches.size());
    matches.forEach(System.out::println);
  }

}
