import java.util.*

fun main() {
    val N = readln().toInt()
    val st = StringTokenizer(readln())
    val A = IntArray(N) { st.nextToken().toInt() }
    var l = 0
    var r = N - 1
    var res = 0
    while (l < r) {
        val score = (r - l - 1) * minOf(A[l], A[r])
        res = maxOf(res, score)
        if (A[l] < A[r]) l++
        else r--
    }
    println(res)
}