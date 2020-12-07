class Day1(input: List<String>) {

    private val entries = input.map { it.toInt() }

    fun solve1(): Int {
        for (a in entries) for (b in entries) if (a + b == 2020) return a * b
        throw IllegalStateException("Solution not found.")
    }

    fun solve2(): Int {
        for (a in entries) for (b in entries) for (c in entries) if (a + b + c == 2020) return a * b * c
        throw IllegalStateException("Solution not found.")
    }
}