package kh.farrukh.aoc2024.day5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import kotlin.Pair;

public class Day5A {

  public static void main(String[] args) {
    // 1st list is which numbers must be before the key
    // 2nd list is which numbers must be after the key
    var rules = new HashMap<Integer, Pair<List<Integer>, List<Integer>>>();
    var instructions = new ArrayList<List<Integer>>();

    var sc = new Scanner(System.in);

    var isPart1 = true;

    while (sc.hasNextLine()) {
      var line = sc.nextLine();
      if (line.isEmpty()) {
        if (isPart1) {
          isPart1 = false;
          continue;
        } else {
          break;
        }
      }

      if (isPart1) {
        var numbers = line.split("\\|");
        var beforeNumber = Integer.parseInt(numbers[0].trim());
        var afterNumber = Integer.parseInt(numbers[1].trim());

        var beforeRule = rules.getOrDefault(
            beforeNumber, new Pair<>(new ArrayList<>(), new ArrayList<>())
        );
        beforeRule.component2().add(afterNumber);
        rules.put(beforeNumber, beforeRule);

        var afterRule = rules.getOrDefault(
            afterNumber, new Pair<>(new ArrayList<>(), new ArrayList<>())
        );
        afterRule.component1().add(beforeNumber);
        rules.put(afterNumber, afterRule);
      } else {
        var numbers = line.split(",");
        var instruction = new ArrayList<Integer>();
        for (var number : numbers) {
          instruction.add(Integer.parseInt(number.trim()));
        }
        instructions.add(instruction);
      }
    }

    var res = 0;
    var validInstructions = new ArrayList<List<Integer>>();

    for (var instruction : instructions) {
      var passedNumbers = new ArrayList<Integer>();
      var isValidInstruction = true;

      for (var number : instruction) {
        var rule = rules.get(number);
        if (rule == null) {
          passedNumbers.add(number);
          continue;
        }

        var beforeNumbers = rule.component1();
        var afterNumbers = rule.component2();

        var hasInvalidPassedNumber = passedNumbers
            .stream()
            .anyMatch(afterNumbers::contains);

        if (hasInvalidPassedNumber) {
          passedNumbers.add(number);
          isValidInstruction = false;
          break;
        }

        passedNumbers.add(number);
      }

      if (isValidInstruction) {
        var middleNumber = instruction.get(instruction.size() / 2);
        res += middleNumber;
        validInstructions.add(instruction);
      }
    }

    System.out.println(res);
    validInstructions.forEach(System.out::println);
  }

}
