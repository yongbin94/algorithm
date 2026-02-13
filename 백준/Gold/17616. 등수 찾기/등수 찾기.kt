import java.util.*

fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val X = st.nextToken().toInt()

    val A = Array(N + 1) { mutableListOf<Int>() }
    val B = Array(N + 1) { mutableListOf<Int>() }
    repeat(M) {
        st = StringTokenizer(readln())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        A[a].add(b)
        B[b].add(a)
    }

    var u = bfs(X, B)
    var v = N - bfs(X, A) + 1

    println("$u $v")
}

fun bfs(x: Int, a: Array<MutableList<Int>>): Int {
    var res = 1
    val q = ArrayDeque<Int>()
    val visited = BooleanArray(a.size)
    q.add(x)
    visited[x] = true
    while (q.isNotEmpty()) {
        val u = q.poll()
        for (v in a[u]) {
            if (visited[v]) continue
            visited[v] = true
            q.add(v)
            res++
        }
    }
    return res
}