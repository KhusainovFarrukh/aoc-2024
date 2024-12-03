package kh.farrukh.aoc2024.day2

import java.util.*
import kotlin.math.abs

fun isLineSafe(report: List<Int>): Boolean {
    val diff = report.zipWithNext { a, b -> a - b }
    return diff.all { it in -3..3 } &&
            (diff.all { it > 0 } || diff.all { it < 0 })
}

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
        var hasOneIncorrect = false
        var isIncreasing = report[1] > report[0]

        var startIdx = 1
        var prevIdx = 0

        val firstDiff = abs(report[1] - report[0])
        if (firstDiff > 3 || firstDiff < 1) {
            hasOneIncorrect = true
            if (report.size == 2) {
                isValidReport = false
            }
            val diffA = abs(report[2] - report[1])
            val diffB = abs(report[2] - report[0])
            if (diffA <= 3 && diffA >= 1) {
                startIdx = 2
                prevIdx = 1
                isIncreasing = report[2] > report[1]
            } else if (diffB <= 3 && diffB >= 1) {
                startIdx = 2
                prevIdx = 0
                isIncreasing = report[2] > report[0]
            } else {
                isValidReport = false
            }
        }

        for (i in startIdx until report.size) {
            val current = report[i]
            val prev = report[prevIdx]

            if (!check(current, prev, isIncreasing)) {
                if (hasOneIncorrect) {
                    isValidReport = false
                    break
                } else {
                    hasOneIncorrect = true
                    prevIdx = i - 1
                }
            } else {
                prevIdx = i
            }
        }

        if (isValidReport) {
            validCount++
        }
    }

    println(validCount)
}