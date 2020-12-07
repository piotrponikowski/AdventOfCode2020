import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day2Test : FunSpec({

    val exampleInput = Utils.readLines("day2.txt", true)
    val realInput = Utils.readLines("day2.txt")

    context("Part 1") {

        test("should parse example input") {
            val policies = Day2(exampleInput).parse();
            policies.size shouldBe 3

            policies[0].from shouldBe 1
            policies[0].to shouldBe 3
            policies[0].symbol shouldBe 'a'
            policies[0].text shouldBe "abcde"

            policies[1].from shouldBe 1
            policies[1].to shouldBe 3
            policies[1].symbol shouldBe 'b'
            policies[1].text shouldBe "cdefg"

            policies[2].from shouldBe 2
            policies[2].to shouldBe 9
            policies[2].symbol shouldBe 'c'
            policies[2].text shouldBe "ccccccccc"
        }

        test("should validate example policies") {
            val policies = Day2(exampleInput).parse();

            policies[0].isValidByOldRule() shouldBe true
            policies[1].isValidByOldRule() shouldBe false
            policies[2].isValidByOldRule() shouldBe true
        }

        test("should solve example") {
            val answer = Day2(exampleInput).solve1()
            answer shouldBe 2
        }

        test("should solve real input") {
            val answer = Day2(realInput).solve1()
            answer shouldBe 638
        }
    }

    context("Part 2"){

        test("should validate example policies") {
            val policies = Day2(exampleInput).parse();

            policies[0].isValidByNewRule() shouldBe true
            policies[1].isValidByNewRule() shouldBe false
            policies[2].isValidByNewRule() shouldBe false
        }

        test("should solve example") {
            val answer = Day2(exampleInput).solve2()
            answer shouldBe 1
        }

        test("should solve real input") {
            val answer = Day2(realInput).solve2()
            answer shouldBe 699
        }
    }

})