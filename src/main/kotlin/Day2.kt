class Day2(private val input: List<String>) {

    private val pattern = Regex("""^(\d+)-(\d+) (\w): (\w+)$""")

    fun parse() = input.map { pattern.matchEntire(it)!!.destructured }
        .map { (from, to, symbol, text) -> PasswordPolicy(from.toInt(), to.toInt(), symbol.single(), text) }

    fun solve1() = parse().filter { it.isValidByOldRule() }.count()
    
    fun solve2() = parse().filter { it.isValidByNewRule() }.count()

    class PasswordPolicy(val from: Int, val to: Int, val symbol: Char, val text: String) {
        fun isValidByOldRule() = text.count { it == symbol } in from..to
        fun isValidByNewRule() = arrayOf(from, to).filter { text[it - 1] == symbol }.count() == 1
    }
}