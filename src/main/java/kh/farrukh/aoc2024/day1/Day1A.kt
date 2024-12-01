package kh.farrukh.aoc2024.day1

import java.util.Scanner
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

    list1.sort()
    list2.sort()

    var totalDiff = 0

    for (i in 0 until list1.size) {
        val num1 = list1.elementAt(i)
        val num2 = list2.elementAt(i)
        val diff = abs(num1 - num2)
        totalDiff += diff
    }

    println(totalDiff)
}
