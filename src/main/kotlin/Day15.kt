class Day15(input: String) {

    private val numbers = input.split(",").map { it.toInt() }

    fun solve1() = playUntil(2020)

    fun solve2() = playUntil(30_000_000)

    private fun playUntil(endTurn: Int): Int {
        val indexes = IntArray(endTurn) { -1 }

        numbers.dropLast(1).forEachIndexed { index, number -> indexes[number] = index + 1 }
        var lastSpoken = numbers.last()

        for (currentIndex in numbers.size until endTurn) {
            val prevIndex = indexes[lastSpoken]
            val nextNumber = if (prevIndex < 0) 0 else (currentIndex) - prevIndex

            indexes[lastSpoken] = currentIndex
            lastSpoken = nextNumber
        }

        return lastSpoken
    }
}

