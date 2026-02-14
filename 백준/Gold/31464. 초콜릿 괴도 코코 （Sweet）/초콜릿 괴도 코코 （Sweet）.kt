import java.util.*

var N = 0
lateinit var map: Array<CharArray>

val dr = intArrayOf(-1, 0, 1, 0)
val dc = intArrayOf(0, 1, 0, -1)

fun main() {
    N = readln().toInt()
    map = Array(N) { readln().toCharArray() }
    val total = map.sumOf { row -> row.count { it == '#' } }
    val result = mutableListOf<Pair<Int, Int>>()

    var cnt = 0
    for (r in 0 until N) {
        for (c in 0 until N) {
            if (map[r][c] == '#') {
                if (r + 1 < N && map[r + 1][c] == '#') cnt++
                if (c + 1 < N && map[r][c + 1] == '#') cnt++
            }
        }
    }

    for (r in 0 until N) {
        for (c in 0 until N) {
            if (map[r][c] == '.') continue
            val tmp = (0 until 4).count {
                val nr = r + dr[it]
                val nc = c + dc[it]
                isIn(nr, nc) && map[nr][nc] == '#'
            }
            if (total - 1 != cnt - tmp + 1) continue

            map[r][c] = '.'
            if (solve(total - 1)) result.add(Pair(r + 1, c + 1))
            map[r][c] = '#'
        }
    }
    val sb = StringBuilder()
    sb.append("${result.size}\n")
    for ((r, c) in result) sb.append("$r $c\n")
    println(sb)
}


fun solve(total: Int): Boolean {
    for (r in 0 until N) {
        for (c in 0 until N) {
            if (map[r][c] == '.') continue
            return total == bfs(r, c)
        }
    }
    return false
}

fun bfs(sr: Int, sc: Int): Int {
    var res = 1
    val q = ArrayDeque<Pair<Int, Int>>()
    val visited = Array(N) { BooleanArray(N) }
    q.add(Pair(sr, sc))
    visited[sr][sc] = true
    while (q.isNotEmpty()) {
        val (r, c) = q.poll()
        for (d in 0 until 4) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == '.') continue
            visited[nr][nc] = true
            q.offer(Pair(nr, nc))
            res++
        }
    }
    return res
}

fun isIn(r: Int, c: Int) = r in 0 until N && c in 0 until N