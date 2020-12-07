import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day4Test : FunSpec({

    val exampleInput = Utils.readText("day4.txt", true)
    val realInput = Utils.readText("day4.txt")

    val valid = Utils.readText("day4-valid.txt", true)
    val invalid = Utils.readText("day4-invalid.txt", true)

    context("Part 1") {

        test("should parse example") {
            val passports = Day4(exampleInput).parse()
            passports.size shouldBe 4

            passports[0].fields.size shouldBe 8
            passports[1].fields.size shouldBe 7
            passports[2].fields.size shouldBe 7
            passports[3].fields.size shouldBe 6
        }

        test("should validate example") {
            val passports = Day4(exampleInput).parse()
            passports.size shouldBe 4

            passports[0].hasMandatoryFields() shouldBe true
            passports[1].hasMandatoryFields() shouldBe false
            passports[2].hasMandatoryFields() shouldBe true
            passports[3].hasMandatoryFields() shouldBe false
        }

        test("should solve example") {
            val answer = Day4(exampleInput).solve1()
            answer shouldBe 2
        }

        test("should solve real input") {
            val answer = Day4(realInput).solve1()
            answer shouldBe 264
        }

    }

    context("Part 2 ") {

        context("should validate field") {
            forAll(
                row("byr", "2002", true),
                row("byr", "2003", false),
                row("hgt", "60in", true),
                row("hgt", "190cm", true),
                row("hgt", "190in", false),
                row("hgt", "190", false),
                row("hcl", "#123abc", true),
                row("hcl", "#123abz", false),
                row("hcl", "123abc", false),
                row("ecl", "brn", true),
                row("ecl", "wat", false),
                row("pid", "000000001", true),
                row("pid", "0123456789", false)
            ) { type, value, valid ->
                test("with type $type and value $value") {
                    Day4.Field(type, value).isValid() shouldBe valid
                }
            }
        }

        test("should recognize valid passports") {
            val passports = Day4(valid).parse()
            passports.forEach { it.isValid() shouldBe true }
        }

        test("should recognize invalid passports") {
            val passports = Day4(invalid).parse()
            passports.forEach { it.isValid() shouldBe false }
        }

        test("should solve real input") {
            val answer = Day4(realInput).solve2()
            answer shouldBe 224
        }
    }
})