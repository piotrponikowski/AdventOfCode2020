import java.lang.System.lineSeparator
import java.util.*

class Day22(input: String) {

    val decks = parseDecks(input)

    fun solve2() {
        val (deck1, deck2) = decks
        val winner = play(0, deck1, deck2)
        println("winner $winner")
    }

    fun play(depth: Int, deck1: ArrayDeque<Int>, deck2: ArrayDeque<Int>): Boolean {
        //println("new game: $depth $deck1 vs $deck2")

        val states = mutableSetOf<String>()
        while (!deck1.isEmpty() && !deck2.isEmpty()) {
            val state = deck1.joinToString(",") { it.toString() } + "#" + deck2.joinToString(",") { it.toString() }

            val card1 = deck1.pop()
            val card2 = deck2.pop()

            val turnWin = if (states.contains(state)) {
                true
            } else if (card1 <= deck1.size && card2 <= deck2.size) {
                play(depth+1, ArrayDeque(deck1.filterIndexed { index, _ -> index < card1 }),
                    ArrayDeque(deck2.filterIndexed { index, _ -> index < card2 }))
            } else {
                card1 > card2
            }

            if (turnWin) {
                deck1.addLast(card1)
                deck1.addLast(card2)
            } else {
                deck2.addLast(card2)
                deck2.addLast(card1)
            }

            states.add(state)
//            println("after turn: $deck1 vs $deck2")
        }

        if(depth == 0) {
            val score =  (if(deck1.isNotEmpty()) deck1 else deck2).reversed()
                .mapIndexed { index, card ->  card * (index + 1)}.reduce(Int::plus)
            println("result: $depth $deck1 vs $deck2, score; $score")
        }

        return deck1.size > 0
    }

    companion object {
        fun parseDecks(input: String) = input.split(lineSeparator().repeat(2))
            .map { data -> data.split(lineSeparator()).drop(1).map { it.toInt() } }
            .map { cards -> ArrayDeque(cards) }
    }
}

fun main() {

    val input = Utils.readText("day22.txt")
    val day = Day22(input)
    println(day.decks)
    day.solve2()

}