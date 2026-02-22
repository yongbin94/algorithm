import java.util.*

val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val R = st.nextToken().toInt()
    val map = Array(N) {
        st = StringTokenizer(readln())
        IntArray(M) { st.nextToken().toInt() }
    }
    val res = Array(N) { IntArray(M) }
    for (i in 0 until (minOf(N, M) / 2)) {
        val n = N - i
        val m = M - i
        var tr = i
        var tc = i
        var td = 0
        val x = 2 * (N - 2 * i) + 2 * (M - 2 * i) - 4
        repeat(R % x) {
            if (tr + dr[td] !in i..<n || tc + dc[td] !in i..<m) td++
            tr += dr[td]
            tc += dc[td]
        }
        var r = i
        var c = i
        var d = 0
        repeat(x) {
            res[r][c] = map[tr][tc]
            if (r + dr[d] !in i..<n || c + dc[d] !in i..<m) d++
            if (tr + dr[td] !in i..<n || tc + dc[td] !in i..<m) td = (td + 1) % 4
            r += dr[d]
            c += dc[d]
            tr += dr[td]
            tc += dc[td]
        }
    }
    println(res.joinToString("\n") { row -> row.joinToString(" ") })
}