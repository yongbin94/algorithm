import java.util.*

fun main() {
    var st = StringTokenizer(readln())
    val R1 = st.nextToken().toInt()
    val C1 = st.nextToken().toInt()

    st = StringTokenizer(readln())
    val R2 = st.nextToken().toInt()
    val C2 = st.nextToken().toInt()

    val dr = arrayOf(
        intArrayOf(-1, -2, -3), intArrayOf(-1, -2, -3),
        intArrayOf(1, 2, 3), intArrayOf(1, 2, 3),
        intArrayOf(0, -1, -2), intArrayOf(0, 1, 2),
        intArrayOf(0, -1, -2), intArrayOf(0, 1, 2)
    )
    val dc = arrayOf(
        intArrayOf(0, -1, -2), intArrayOf(0, 1, 2),
        intArrayOf(0, -1, -2), intArrayOf(0, 1, 2),
        intArrayOf(-1, -2, -3), intArrayOf(-1, -2, -3),
        intArrayOf(1, 2, 3), intArrayOf(1, 2, 3)
    )

    val q = ArrayDeque<IntArray>()
    val visited = Array(10) { BooleanArray(9) }

    q.offer(intArrayOf(R1, C1, 0))
    visited[R1][C1] = true

    while (q.isNotEmpty()) {
        val cur = q.poll()
        val r = cur[0]
        val c = cur[1]
        val cnt = cur[2]

        if (r == R2 && c == C2) {
            println(cnt)
            return
        }

        for (i in 0 until 8) {
            var canMove = true
            for (j in 0 until 2) {
                val nr = r + dr[i][j]
                val nc = c + dc[i][j]
                if (nr !in 0..9 || nc !in 0..8 || (nr == R2 && nc == C2)) {
                    canMove = false
                    break
                }
            }

            if (canMove) {
                val nr = r + dr[i][2]
                val nc = c + dc[i][2]
                if (nr in 0..9 && nc in 0..8 && !visited[nr][nc]) {
                    visited[nr][nc] = true
                    q.offer(intArrayOf(nr, nc, cnt + 1))
                }
            }
        }
    }
    println(-1)
}