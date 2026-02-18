import java.util.*

fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val A = IntArray(N) { readln().toInt() }
    val dp = IntArray(N + 1) { Int.MAX_VALUE }
    dp[N] = 0
    for (i in N - 1 downTo 0) {
        var v = 0
        for (j in i until N) {
            v += if (j == i) A[j] else A[j] + 1
            if (v > M) break
            if (j == N - 1) {
                dp[i] = 0
                break
            } else if (dp[j + 1] != Int.MAX_VALUE) {
                dp[i] = Math.min(dp[i], (M - v) * (M - v) + dp[j + 1])
            }
        }
    }
    println(dp[0])
}