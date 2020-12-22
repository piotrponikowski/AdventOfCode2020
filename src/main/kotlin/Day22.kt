import java.lang.System.lineSeparator
import kotlin.collections.ArrayList

class Day22(input: String) {

    val decks = parseDecks(input)

    fun solve1() = decks.let { (deck1, deck2) -> play(deck1, deck2).second.score() }

    fun solve2() = decks.let { (deck1, deck2) -> playWithSubGame(deck1, deck2).second.score() }

    private fun play(deck1: ArrayList<Int>, deck2: ArrayList<Int>): Pair<Boolean, List<Int>> {
        while (deck1.isNotEmpty() && deck2.isNotEmpty()) {
            val card1 = deck1.removeFirst()
            val card2 = deck2.removeFirst()

            if (card1 > card2) {
                deck1.add(card1)
                deck1.add(card2)
            } else {
                deck2.add(card2)
                deck2.add(card1)
            }
        }

        return if (deck1.size > 0) (true to deck1) else (false to deck2)
    }

    private fun playWithSubGame(deck1: ArrayList<Int>, deck2: ArrayList<Int>): Pair<Boolean, List<Int>> {

        val states = mutableSetOf<Pair<List<Int>, List<Int>>>()
        while (deck1.isNotEmpty() && deck2.isNotEmpty()) {

            if (!states.add(Pair(deck1.toList(), deck2.toList()))) {
                return (true to deck1)
            }

            val card1 = deck1.removeFirst()
            val card2 = deck2.removeFirst()

            val win = if (card1 <= deck1.size && card2 <= deck2.size) {
                playWithSubGame(ArrayList(deck1.take(card1)), ArrayList(deck2.take(card2))).first
            } else {
                card1 > card2
            }

            if (win) {
                deck1.add(card1)
                deck1.add(card2)
            } else {
                deck2.add(card2)
                deck2.add(card1)
            }
        }

        return if (deck1.size > 0) (true to deck1) else (false to deck2)
    }

    private fun List<Int>.score() = reversed().mapIndexed { index, card -> card * (index + 1) }.sum()

    companion object {
        fun parseDecks(input: String) = input.split(lineSeparator().repeat(2))
            .map { data -> data.split(lineSeparator()).drop(1).map { it.toInt() } }
            .map { ArrayList(it) }
    }
}