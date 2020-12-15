class Day15(input: String) {

    val numbers = input.split(",").map { it.toInt() }

    val game = sequence {
        val indexes = mutableMapOf<Int, Int>()

        numbers.take(numbers.size - 1).forEachIndexed { index, number ->
            indexes[number] = index + 1
            yield(number)
        }

        generateSequence(Pair(numbers.last(), numbers.size)) { (lastSpoken, currentIndex) ->
            val prevIndex = indexes[lastSpoken] ?: -1
            indexes[lastSpoken] = currentIndex
            val result = if (prevIndex < 0) 0 else currentIndex - prevIndex

            Pair(result, currentIndex + 1)
        }.forEach { (number) -> yield(number) }
    }

}

fun main() {

    val day = Day15("0,3,6")

    val numbers = day.game.filterIndexed { index, number -> index == 30_000_000 - 1 }.first()
    println(numbers)


//    day.game.take(100).toList().forEach {
//        println(it)
//    }

}

