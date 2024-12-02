import utils.getLinesFromFileInResources
import kotlin.math.abs

class Day2 {
    fun numberOfSafeReports(): Int = getReports().count { isReportSafe(it) }

    fun numberOfSafeReportsWithDampener(): Int = getReports().filterNot { isReportSafe(it) }.filter { report ->
        for (i in report.indices) {
            val tmp = report.toMutableList()
            tmp.removeAt(i)
            if (isReportSafe(tmp)) return@filter true
        }
        false
    }.count() + numberOfSafeReports()

    private fun isReportSafe(report: List<Int>): Boolean {
        val increasing = report[1] - report.first() > 0
        var prevLevel = report.first()

        report.drop(1).forEach { level ->
            if ((increasing && level <= prevLevel) || (!increasing && level >= prevLevel)) {
                return false
            } else {
                if (abs(level - prevLevel) > 3) return false
            }
            prevLevel = level
        }

        return true
    }

    private fun getReports() = getLinesFromFileInResources("/Day2")
        .map { it.split(" ") }
        .map { it.map(String::toInt) }
}

fun main() {
    println(Day2().numberOfSafeReportsWithDampener())
}