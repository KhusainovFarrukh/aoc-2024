package kh.farrukh.aoc2024.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day1B {

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

    var similarity = new HashMap<Integer, Integer>();

    for (int i = 0; i < list1.size(); i++) {
      var n1 = list1.get(i);
      var simCount = list2.stream().filter(x -> x.equals(n1)).count();
      similarity.put(n1, (int) simCount);
    }

    var res = 0;

    for (var entry : similarity.entrySet()) {
      res += entry.getKey() * entry.getValue();
    }

    System.out.println(res);
  }

}
