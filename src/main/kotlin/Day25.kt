class Day25(input: List<String>) {

    private val card = input[0].toLong()
    private val door = input[1].toLong()

    fun solve1() = encryptionKey(door, loopSize(card))

    companion object {
        fun loopSize(key: Long): Int {
            var value = 1L
            var loopSize = 0
            while (value != key) {
                value = (value * 7L) % 20201227
                loopSize++
            }
            return loopSize
        }

        fun encryptionKey(key: Long, loopSize: Int): Long {
            var value = 1L
            repeat(loopSize) { value = (value * key) % 20201227 }
            return value
        }
    }
}
