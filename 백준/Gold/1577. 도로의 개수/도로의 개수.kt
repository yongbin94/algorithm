import java.util.*

fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val K = readln().toInt()

    val isClosed = Array(N + 1) { Array(M + 1) { BooleanArray(2) } }

    repeat(K) {
        val line = StringTokenizer(readln())
        val a = line.nextToken().toInt()
        val b = line.nextToken().toInt()
        val c = line.nextToken().toInt()
        val d = line.nextToken().toInt()

        if (a == c) {
            isClosed[a][minOf(b, d)][1] = true
        } else {
            isClosed[minOf(a, c)][b][0] = true
        }
    }

    val dp = Array(N + 1) { LongArray(M + 1) }
    dp[0][0] = 1L

    for (i in 0..N) {
        for (j in 0..M) {
            if (i + 1 <= N && !isClosed[i][j][0]) {
                dp[i + 1][j] += dp[i][j]
            }
            if (j + 1 <= M && !isClosed[i][j][1]) {
                dp[i][j + 1] += dp[i][j]
            }
        }
    }
    println(dp[N][M])
}