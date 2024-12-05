import utils.getLinesFromFileInResources

class Day3 {
    fun sumOfValidMulOperations() = findOperandsInMulOperations().sumOf { it.mul() }

    fun sumOfValidAndEnabledMulOperations() = findEnabledMulOperations().sumOf { it.mul() }

    fun test() = getLinesFromFileInResources("/Day3")
        .flatMap { it.split("do()") }
        .map { candidate ->
            val indexOfDont = candidate.indexOf("don't()")
            if (indexOfDont > 0) candidate.substring(0..indexOfDont) else candidate
        }
        .flatMap { Regex("mul\\((\\d+),(\\d+)\\)").findAll(it) }
        .map { it.groupValues.drop(1) }
        .map { it.map(String::toInt) }
        .sumOf { it.mul() }

    private fun findOperandsInMulOperations() = getLinesFromFileInResources("/Day3")
        .flatMap { Regex("mul\\((\\d+),(\\d+)\\)").findAll(it) }
        .map { it.groupValues.drop(1) }
        .map { it.map(String::toInt) }

    private fun findEnabledMulOperations() = getLinesFromFileInResources("/Day3")
        .flatMap {
            val pattern =
                Regex("(?<=do\\(\\).*)mul\\(\\d{1,3},\\d{1,3}\\)|(?<!don't\\(\\).*)mul\\(\\d{1,3},\\d{1,3}\\)")
            pattern.findAll(it)
        }
        .map { it.value.replace(Regex("[mul(|)]"), "").split(",") }
        .map { it.map(String::toInt) }

    private fun List<Int>.mul() = this.reduce(Int::times)
}

fun main() {
    println(Day3().test())
}