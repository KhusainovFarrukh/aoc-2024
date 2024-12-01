package kh.farrukh.aoc2024.day1

import java.util.Scanner
import java.util.stream.Collectors
import kotlin.math.abs


fun main() {
    val sc = Scanner(System.`in`)

    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    var line = sc.nextLine()

    while (line != null && line.isNotBlank()) {
        val nums = line.split("   ")
        list1.add(nums[0].toInt())
        list2.add(nums[1].toInt())

        line = sc.nextLine()
    }

    val list2Map = list2.groupingBy { it }.eachCount()

    var res = 0

    list1.forEach {
        list2Map[it]?.let { count -> res += it * count }
    }

    println(res)
}
