import java.util.*

fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val X = st.nextToken().toInt()
    val Y = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    var dp = Array(N + 1) { DoubleArray(N + 1) }

    dp[X][Y] = 1.0

    val dr = intArrayOf(-2, -2, -1, -1, 1, 1, 2, 2)
    val dc = intArrayOf(-1, 1, -2, 2, -2, 2, -1, 1)

    repeat(K) {
        val tmp = Array(N + 1) { DoubleArray(N + 1) }

        for (r in 1..N) {
            for (c in 1..N) {
                if (dp[r][c] == 0.0) continue

                for (d in 0 until 8) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]

                    if (nr in 1..N && nc in 1..N) {
                        tmp[nr][nc] += dp[r][c] / 8.0
                    }
                }
            }
        }
        dp = tmp
    }

    println(dp.sumOf { it.sum() })
}