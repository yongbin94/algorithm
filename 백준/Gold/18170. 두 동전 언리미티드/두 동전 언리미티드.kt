import java.util.*

var N = 0
var M = 0
lateinit var map: Array<CharArray>
val dr = intArrayOf(-1, 0, 1, 0)
val dc = intArrayOf(0, 1, 0, -1)


fun main() {
    val st = StringTokenizer(readln())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    map = Array(N) { readln().toCharArray() }

    val coins = mutableListOf<Pair<Int, Int>>()
    for (r in 0 until N) {
        for (c in 0 until M) {
            if (map[r][c] == 'o') coins.add(Pair(r, c))
        }
    }
    println(bfs(Info(coins[0].first, coins[0].second, coins[1].first, coins[1].second)))
}

fun bfs(start: Info): Int {
    val visited = HashSet<Info>()
    val q = ArrayDeque<Info>()

    q.add(start)
    visited.add(start)

    var dist = 0
    while (q.isNotEmpty()) {
        val size = q.size
        for (i in 0 until size) {
            val curr = q.poll()
            for (d in 0 until 4) {
                var nr1 = curr.r1 + dr[d]
                var nc1 = curr.c1 + dc[d]
                var nr2 = curr.r2 + dr[d]
                var nc2 = curr.c2 + dc[d]

                val out1 = !isIn(nr1, nc1)
                val out2 = !isIn(nr2, nc2)

                if (out1 xor out2) return dist + 1
                if (out1 && out2) continue

                if (map[nr1][nc1] == '#') {
                    nr1 = curr.r1
                    nc1 = curr.c1
                }
                if (map[nr2][nc2] == '#') {
                    nr2 = curr.r2
                    nc2 = curr.c2
                }

                val next = Info(nr1, nc1, nr2, nc2)
                if (next in visited) continue
                visited.add(next)
                q.add(next)
            }
        }
        dist++
    }
    return -1
}

fun isIn(r: Int, c: Int) = r in 0 until N && c in 0 until M
data class Info(val r1: Int, val c1: Int, val r2: Int, val c2: Int)