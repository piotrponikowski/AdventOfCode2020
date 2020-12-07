class Day5(input: List<String>) {

    private val seatIds = input.map { decodeSeatId(it) }.sorted()

    fun solve1() = seatIds.last()

    fun solve2() = seatIds.zipWithNext().first { (curr, next) -> next - curr == 2 }.let { (curr) -> curr + 1 }

    companion object {
        fun decodeSeatId(input: String) = input
            .replace(Regex("[BR]"), "1")
            .replace(Regex("[FL]"), "0")
            .toInt(2)
    }
}
