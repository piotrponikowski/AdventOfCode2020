class Day9(input: List<String>, private val preamble: Int = 25) {

    private val numbers = input.map { it.toLong() }

    fun solve1() = numbers.windowed(preamble + 1)
        .map { Pair(it.dropLast(1), it.last()) }
        .first { (list, target) -> list.none { target - it in list } }
        .let { (_, target) -> target }

    fun solve2() = solve1().let { invalid ->
        numbers.indices.mapNotNull { start ->
            numbers.indices.drop(start + 1).mapNotNull { end ->
                numbers.subList(start, end).takeIf { it.sum() == invalid }
            }.firstOrNull()
        }.first().let { (it.maxOrNull() ?: 0) + (it.minOrNull() ?: 0) }
    }

}