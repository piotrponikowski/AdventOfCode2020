class Day9(input: List<String>) {

    private val numbers = input.map { it.toLong() }

    fun solve1(preamble: Int) = (preamble until numbers.size)
        .filterNot { i -> (i - preamble until i).any { p1 -> (i - preamble until i).any { p2 -> p1 != p2 && numbers[p1] + numbers[p2] == numbers[i] } } }
        .first().let { numbers[it] }

    fun solve2(invalid: Long): Long {
        for ((startIndex, start) in numbers.withIndex()) {
            val sum = mutableListOf<Long>(start)

            for ((nextIndex, next) in numbers.withIndex()) {
                if (nextIndex <= startIndex) {
                    continue
                }

                sum += next

                if (sum.sum() == invalid) {
                    return sum.minOrNull()!! + sum.maxOrNull()!!
                } else if (sum.sum() > invalid) {
                    break
                }
            }
        }

        throw IllegalStateException("Solution not found.")

    }
}