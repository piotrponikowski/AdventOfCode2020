class Day7(input: List<String>) {

    val bags = input.map { Bag.parse(it) }

    fun solve1() = countUp("shiny gold").distinct().count()

    fun solve2() = sumDown("shiny gold") - 1

    private fun sumDown(color: String): Int = 1 + bags
        .first { bag -> bag.color == color }.content
        .map { (color, count) -> count * sumDown(color) }.sum()

    private fun countUp(color: String): List<Bag> = bags
        .filter { bag -> bag.content.any { content -> content.key == color } }
        .let { matchedBags -> matchedBags + matchedBags.flatMap { countUp(it.color) } }

    class Bag(val color: String, val content: Map<String, Int>) {

        companion object {
            private val REGEX_NAME = Regex("^(.*?) bag")
            private val REGEX_CONTENT = Regex("([0-9])+ (.*?) bag")

            fun parse(input: String): Bag {
                val (name) = REGEX_NAME.find(input)!!.destructured
                val contains = REGEX_CONTENT.findAll(input).map { it.destructured }
                    .associateBy({ (_, color) -> color }, { (count) -> count.toInt() })

                return Bag(name, contains)
            }
        }
    }
}
