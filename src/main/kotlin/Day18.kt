class Day18(val input: List<String>) {

    val pattern = Regex("""\(([0-9+* ]+)\)""")

    fun solve1() = input.map { calculate(it) }.sum()

    fun calculate(input: String): Long {
        var expression = input
        println(expression)

        while (expression.contains("(")) {
            var match = pattern.find(expression)!!
            val groups = match.groups
            val range = groups[0]!!.range
            val innerExpression = groups[1]!!.value
            expression = expression.replaceRange(range, resolveExpression(innerExpression).toString())
        }

        return resolveExpression(expression)
    }

    fun resolveExpression(input: String): Long {
        var expression = input

        val patternPlus = Regex("""([0-9]+) \+ ([0-9]+)""")
        val patternMul = Regex("""([0-9]+) \* ([0-9]+)""")

        while (expression.contains("+")) {
            var match = patternPlus.find(expression)!!
            val range = match.groups[0]!!.range
            val (arg1, arg2) = match.destructured
            expression = expression.replaceRange(range, (arg1.toLong() + arg2.toLong()).toString())
        }

        while (expression.contains("*")) {

            var match = patternMul.find(expression)!!
            val groups = match.groups
            val range = groups[0]!!.range
            val innerExpression = groups[0]!!.value
            val r = innerExpression.split(" * ").map { it.toLong() }.reduce { a, b -> a * b }
            expression = expression.replaceRange(range, r.toString())
        }


        return expression.toLong()


//        val instructions = input.split(" ")
//        var lastOperator = ""
//        return instructions.drop(1).fold(instructions.first().toLong()) { result, instruction ->
//            var r = result
//            when (instruction) {
//                "+" -> lastOperator = instruction
//                "*" -> lastOperator = instruction
//                else -> r = if (lastOperator == "+") result + instruction.toLong() else result * instruction.toLong()
//            }
//
//            r
//        }
    }


}

fun main() {

    val input = Utils.readLines("day18.txt")
    val day = Day18(input)
//    val result = day.resolveExpression("1 + 2 * 3 + 4 * 5 + 6")
    val result = day.solve1()
    println(result)


}

