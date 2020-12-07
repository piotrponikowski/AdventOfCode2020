import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day7Test : FunSpec({

    val exampleInput = Utils.readLines("day7.txt", true)
    val realInput = Utils.readLines("day7.txt")

    context("Part 1") {

        test("should parse colors") {
            val bags = Day7(exampleInput).bags
            bags.size shouldBe 9

            bags[0].color shouldBe "light red"
            bags[1].color shouldBe "dark orange"
            bags[2].color shouldBe "bright white"
            bags[3].color shouldBe "muted yellow"
            bags[4].color shouldBe "shiny gold"
            bags[5].color shouldBe "dark olive"
            bags[6].color shouldBe "vibrant plum"
            bags[7].color shouldBe "faded blue"
            bags[8].color shouldBe "dotted black"
        }

        test("should parse content") {
            val bags = Day7(exampleInput).bags
            bags.size shouldBe 9

            bags[0].content shouldBe mapOf("bright white" to 1, "muted yellow" to 2)
            bags[1].content shouldBe mapOf("bright white" to 3, "muted yellow" to 4)
            bags[2].content shouldBe mapOf("shiny gold" to 1)
            bags[3].content shouldBe mapOf("shiny gold" to 2, "faded blue" to 9)
            bags[4].content shouldBe mapOf("dark olive" to 1, "vibrant plum" to 2)
            bags[5].content shouldBe mapOf("faded blue" to 3, "dotted black" to 4)
            bags[6].content shouldBe mapOf("faded blue" to 5, "dotted black" to 6)
            bags[7].content shouldBe emptyMap()
            bags[8].content shouldBe emptyMap()
        }

        test("should solve example") {
            val answer = Day7(exampleInput).solve1()
            answer shouldBe 4
        }

        test("should solve real input") {
            val answer = Day7(realInput).solve1()
            answer shouldBe 139
        }
    }

    context("Part 2") {

        test("should solve example") {
            val answer = Day7(exampleInput).solve2()
            answer shouldBe 32
        }

        test("should solve real input") {
            val answer = Day7(realInput).solve2()
            answer shouldBe 58175
        }
    }


})