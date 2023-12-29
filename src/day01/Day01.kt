package day01

import println
import readInput

fun main() {
	fun findCalibrationValue(line: String): Int {
		val values: ArrayDeque<Char> = ArrayDeque()
		for (idx in line.indices) {
			if (line[idx].isDigit()) {
				values.add(line[idx])
			}
		}
		return "${values.first()}${values.last()}".toInt()
	}

	fun solveOneStar(input: List<String>): Int {
		return input.sumOf { findCalibrationValue(it) }
	}

	val words: Map<String, Char> = mapOf(
		"one" to '1',
		"two" to '2',
		"three" to '3',
		"four" to '4',
		"five" to '5',
		"six" to '6',
		"seven" to '7',
		"eight" to '8',
		"nine" to '9'
	)

	fun String.possibleWordsAt(leftIndex: Int): List<String> {
		return (3..5).map { len -> substring(leftIndex, (leftIndex + len).coerceAtMost(length)) }
	}

	fun solveTwoStars(input: List<String>): Int {
		return input.sumOf { line ->
			findCalibrationValue(line.mapIndexedNotNull { index, c ->
				if (c.isDigit()) {
					c
				} else {
					line.possibleWordsAt(index).firstNotNullOfOrNull {
						words[it]
					}
				}
			}.joinToString(""))
		}
	}

	val oneStarTestInput = readInput("day01/day01_one_star_test")
	check(solveOneStar(oneStarTestInput) == 142)

	val input = readInput("day01/day01")
	solveOneStar(input).println()

	val twoStarsTestInput = readInput("day01/day01_two_stars_test")
	check(solveTwoStars(twoStarsTestInput) == 281)
	solveTwoStars(input).println()
}
