import utils.getFile
import kotlin.math.abs

class Day1 {
    fun calculateTotalDistance(): Int {
        val (locations1, locations2) = getLocationLists()

        locations1.sort()
        locations2.sort()

        return locations1.zip(locations2).sumOf { abs(it.first - it.second) }
    }

    fun calculateSimilarityScore(): Int {
        val (locations1, locations2) = getLocationLists()

        val locations2Freq = locations2.groupingBy { it }.eachCount()

        return locations1.sumOf { it * (locations2Freq[it] ?: 0) }
    }

    private fun getLocationLists(): Pair<MutableList<Int>, MutableList<Int>> {
        val locations1 = mutableListOf<Int>()
        val locations2 = mutableListOf<Int>()

        getFile("/Day1/Day1Input1").useLines { lines ->
            lines.map { it.split("\\s+".toRegex()) }.forEach {
                locations1.add(it.first().toInt())
                locations2.add(it.last().toInt())
            }
        }

        return locations1 to locations2
    }
}

fun main() {
    println(Day1().calculateSimilarityScore())
}