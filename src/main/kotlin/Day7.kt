class Day7(input: List<String>) {

    val bags = input.map { Bag.parse(it) }

    fun solve1(): Int {
        val outerColors = mutableSetOf<String>()
        val colorsToCheck = mutableSetOf("shiny gold")

        while (colorsToCheck.isNotEmpty()) {
            val color = colorsToCheck.first().also { colorsToCheck.remove(it) }

            val matchedBags = bags.filter { bag -> bag.content.any { content -> content.key == color } }
                .filter { it.color !in outerColors }
                .map { it.color }

            outerColors.addAll(matchedBags)
            colorsToCheck.addAll(matchedBags)
        }

        return outerColors.size
    }

    fun solve2(): Int {
        var result = 0
        val bagsToCount = mutableListOf("shiny gold" to 1)

        while (bagsToCount.isNotEmpty()) {
            val (color, count) = bagsToCount.first().also { bagsToCount.remove(it) }

            val matchedBags = bags.filter { bag -> bag.color == color }
                .flatMap { bag -> bag.content.map { it.key to it.value * count } }

            bagsToCount.addAll(matchedBags)

            result += count
        }

        return result - 1
    }

    class Bag(val color: String, val content: Map<String, Int>) {

        companion object {
            private val nameRegex = Regex("^(.*?) bag")
            private val containsRegex = Regex("([0-9])+ (.*?) bag")

            fun parse(input: String): Bag {
                val (name) = nameRegex.find(input)!!.destructured
                val contains = containsRegex.findAll(input).map { it.destructured }
                    .associateBy({ (_, color) -> color }, { (count) -> count.toInt() })

                return Bag(name, contains)
            }
        }
    }
}
