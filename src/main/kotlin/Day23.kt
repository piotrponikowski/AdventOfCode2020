class Day23(input: String) {

    private val inputValues = input.toCharArray().map { it.toString().toInt() }

    fun solve1(): String {
        val circle = Circle(inputValues)
        repeat(100) { circle.move() }

        circle.wrap(1)
        return (1..8).map { circle.take().value }.toList().joinToString("")
    }

    fun solve2(): Long {
        val circle = Circle(inputValues + (10..1_000_000).toList())
        repeat(10_000_000) { circle.move() }

        circle.wrap(1)
        return circle.take().value.toLong() * circle.take().value.toLong()
    }

    class Circle(values: List<Int>) {
        private val cache = Array(values.size) { Cup(it + 1) }
        private var currentCup: Cup

        init {
            currentCup = cache[values.first() - 1]
            values.drop(1).reversed().forEach { value -> push(cache[value - 1]) }
        }

        private fun push(cup: Cup, after: Cup = currentCup) {
            cup.next = after.next
            after.next = cup
        }

        fun take(): Cup {
            val result = currentCup.next
            currentCup.next = result.next
            return result
        }

        fun wrap(value: Int) {
            currentCup = cache[value - 1]
        }

        fun move() {
            val pickedCups = listOf(take(), take(), take())
            val pickedValues = pickedCups.map { cup -> cup.value }

            var destinationValue = currentCup.value - 1
            while (destinationValue in pickedValues) destinationValue--

            if (destinationValue < 1) {
                destinationValue = cache.size
                while (destinationValue in pickedValues) destinationValue--
            }

            val destinationCup = cache[destinationValue - 1]
            pickedCups.reversed().forEach { push(it, destinationCup) }

            currentCup = currentCup.next
        }
    }

    class Cup(val value: Int) {
        var next: Cup = this
    }
}
