fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach{ it ->
            val number = it.filter { it.isDigit() }
            val lastDigit = (number.toDouble() % 10).toInt()
            val firstDigit = number.first().toString().toInt()


            val numberFirstLast = (firstDigit.toString() + lastDigit.toString()).toInt()

            result += numberFirstLast

        }

        return result
    }

    fun part2(input: List<String>): Int {
        val DIGIT_WORDS = mapOf(
            "one"       to 1,
            "two"       to 2,
            "three"     to 3,
            "four"      to 4,
            "five"      to 5,
            "six"       to 6,
            "seven"     to 7,
            "eight"     to 8,
            "nine"      to 9,
        )

        val startingLetters = DIGIT_WORDS.keys.map { it.first() }.toSet()

        return input.sumOf { line ->
            val lineNumbers = line.mapIndexed { idx, c ->
                if (c.isDigit()) {
                    c - '0'
                } else if (startingLetters.contains(c)) {
                    DIGIT_WORDS.entries.firstOrNull { (word, digit) ->
                        word == line.substring(idx, Math.min(idx + word.length, line.length))
                    }.let { it?.value } // do this better
                } else {
                    null
                }
            }.filterNotNull()

            val concatNumber = "${lineNumbers.first()}${lineNumbers.last()}".toInt()
            concatNumber
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01")
    //part1(testInput).println()
    part2(testInput).println()

    val input = readInput("Day01_test")
    //part1(input).println()
    //part2(input).println()
}
