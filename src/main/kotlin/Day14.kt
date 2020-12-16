import java.lang.System.lineSeparator

class Day14(input: String) {

    private val masks = parse(input)

    fun solve1(): Long {
        val memory = mutableMapOf<Long, Long>()

        masks.forEach { mask ->
            mask.instructions.forEach { instruction ->
                memory[instruction.address] = (instruction.value and mask.enabled()) or mask.disabled()
            }
        }

        return memory.values.sum()
    }

    fun solve2(): Long {
        val memory = mutableMapOf<Long, Long>()

        masks.forEach { mask ->
            mask.instructions.forEach { instruction ->
                val commonPart = (instruction.address and mask.negated()) or mask.disabled()
                mask.combinations().forEach { dynamicPart ->
                    val address = commonPart or dynamicPart
                    memory[address] = instruction.value
                }
            }
        }

        return memory.values.sum()
    }

    companion object {
        private val PATTERN_VALUE = Regex("([0-9]+).*?([0-9]+)")

        fun parse(input: String) = input.split("mask = ")
            .filter { it.isNotBlank() }
            .map { it.split(lineSeparator()) }
            .map { group ->
                val mask = group.first()
                val instructions = group.drop(1).filter { it.isNotBlank() }
                    .map { PATTERN_VALUE.find(it)!!.destructured }
                    .map { (memory, value) -> Instruction(memory.toLong(), value.toLong()) }
                Mask(mask, instructions)
            }
    }


    data class Mask(val value: String, val instructions: List<Instruction>) {
        fun disabled() = value.replace("X", "0").toLong(2)
        fun enabled() = value.replace("X", "1").toLong(2)
        fun negated() = value.replace("0", "1").replace("X", "0").toLong(2)
        fun combinations() = options(value).map { it.toLong(2) }

        private fun options(next: String): List<String> {
            return if (!next.contains("X")) listOf(next)
            else options(next.replaceFirst("X", "1")) + options(next.replaceFirst("X", "0"))
        }
    }

    data class Instruction(val address: Long, val value: Long)
}