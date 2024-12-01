package kh.farrukh.aoc2024.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Day1A {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    var list1 = new ArrayList<Integer>();
    var list2 = new ArrayList<Integer>();

    var l = reader.readLine();

    while (l != null && !l.isEmpty()) {
      var ns = l.split("   ");
      list1.add(Integer.parseInt(ns[0]));
      list2.add(Integer.parseInt(ns[1]));
      l = reader.readLine();
    }

    list1.sort(Comparator.naturalOrder());
    list2.sort(Comparator.naturalOrder());

    var totalDist = 0;

    for (int i = 0; i < list1.size(); i++) {
      totalDist += Math.abs(list1.get(i) - list2.get(i));
    }

    System.out.println(totalDist);
  }

}
