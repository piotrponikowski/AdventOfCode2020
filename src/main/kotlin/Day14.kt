class Day14(input: List<String>) {

    val instructions = parse(input)

    fun solve1(): Long {
        val memory = mutableMapOf<Long, Long>()
        var enabledMask: String
        var disabledMask: String



        instructions.forEach { instruction ->
            when (instruction) {
                is Instruction.SetMask -> println("test")
                is Instruction.SetValue -> println("test")
            }
        }

        return memory.values.sum()
    }


    companion object {
        private val PATTERN_MASK = Regex("[X0-9]+")
        private val PATTERN_VALUE = Regex("([0-9]+).*?([0-9]+)")

        fun parse(input: List<String>) = input.map {
            if (it.contains("mask")) PATTERN_MASK.find(it)!!.groupValues
                .let { (mask) -> Instruction.SetMask(mask) }
            else PATTERN_VALUE.find(it)!!.groupValues
                .let { (addres, value) -> Instruction.SetValue(addres, value.toLong()) }
        }
    }

    sealed class Instruction {
        data class SetMask(val mask: String) : Instruction() {
            fun enabled() = mask.replace('X', '1')
            fun disabled() = mask.replace('X', '0')
        }

        data class SetValue(val address: String, val value: Long) : Instruction()
    }
}