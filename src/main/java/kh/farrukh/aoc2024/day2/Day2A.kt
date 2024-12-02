package kh.farrukh.aoc2024.day2

import java.util.*
import kotlin.math.abs

fun main() {
    val sc = Scanner(System.`in`)

    val reports = mutableListOf<List<Int>>()

    while (sc.hasNextLine()) {
        val line = sc.nextLine()
        if (line.isBlank()) {
            break
        }
        val report = line.split(" ").map { it.toInt() }
        reports.add(report)
    }

    var validCount = 0

    for (report in reports) {
        var isValidReport = true
        val isIncreasing = report[1] > report[0]

        for (i in 1 until report.size) {
            val current = report[i]
            val prev = report[i - 1]

            if (!check(current, prev, isIncreasing)) {
                isValidReport = false
                break
            }
        }

        if (isValidReport) {
            validCount++
        }
    }

    println(validCount)
}

fun check(
    current: Int,
    prev: Int,
    isIncreasing: Boolean
): Boolean {
    if (isIncreasing && current < prev) {
        return false
    }

    if (!isIncreasing && current > prev) {
        return false
    }

    val diff = abs(current - prev)
    return diff in 1..3
}