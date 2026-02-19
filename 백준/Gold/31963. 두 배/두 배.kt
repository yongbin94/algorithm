import java.util.*

fun main() {
    val N = readln().toInt()
    val st = StringTokenizer(readln())
    var prev = st.nextToken().toLong()
    var v = 0L
    var res = 0L
    while (st.hasMoreTokens()) {
        val curr = st.nextToken().toLong()
        var diff = 0

        if (prev > curr) {
            var temp = curr
            while (prev > temp) {
                temp = temp shl 1
                diff++
            }
        } else if (prev < curr) {
            var temp = prev
            while ((temp shl 1) <= curr) {
                temp = temp shl 1
                diff--
            }
        }
        v = maxOf(0L, v + diff)
        res += v
        prev = curr
    }
    println(res)
}