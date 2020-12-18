class Day18(val input: List<String>) {

    fun solve1() = input.map { expression -> reduceByOrder(expression) }.map { it.toLong() }.sum()
    fun solve2() = input.map { expression -> reduceByPriority(expression) }.map { it.toLong() }.sum()

    companion object {
        private val PATTERN_ADD = Regex("""([0-9]+) (\+) ([0-9]+)""")
        private val PATTERN_MUL = Regex("""([0-9]+) (\*) ([0-9]+)""")
        private val PATTERN_ANY = Regex("""([0-9]+) ([*+]) ([0-9]+)""")
        private val PATTERN_BRACES = Regex("""\(([0-9+* ]+)\)""")

        fun evalByOrder(input: String) = eval(input, PATTERN_ANY)
        fun evalByPriority(input: String) = eval(eval(input, PATTERN_ADD), PATTERN_MUL)

        fun reduceByOrder(input: String) = reduce(input, ::evalByOrder)
        fun reduceByPriority(input: String) = reduce(input, ::evalByPriority)

        private fun eval(input: String, regex: Regex): String {
            var expression = input
            while (regex.containsMatchIn(expression)) {
                val match = regex.find(expression)!!
                val range = match.groups[0]!!.range
                val (arg1, operation, arg2) = match.destructured
                val result = if (operation == "+") arg1.toLong() + arg2.toLong() else arg1.toLong() * arg2.toLong()
                expression = expression.replaceRange(range, result.toString())
            }
            return expression
        }

        private fun reduce(input: String, eval: (input: String) -> String): String {
            var expression = input
            while (PATTERN_BRACES.containsMatchIn(expression)) {
                var match = PATTERN_BRACES.find(expression)!!
                val range = match.groups[0]!!.range
                val innerExpression = match.groups[1]!!.value
                expression = expression.replaceRange(range, eval(innerExpression).toString())
            }
            return eval(expression)
        }
    }
}

