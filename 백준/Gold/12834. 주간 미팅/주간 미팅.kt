import java.util.*

fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val V = st.nextToken().toInt()
    val E = st.nextToken().toInt()

    st = StringTokenizer(readln())
    val A = st.nextToken().toInt()
    val B = st.nextToken().toInt()

    val H = IntArray(N)
    st = StringTokenizer(readln())
    for (i in 0..<N) H[i] = st.nextToken().toInt()

    val adj = Array(V + 1) { mutableListOf<Node>() }
    repeat(E) {
        st = StringTokenizer(readln())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        adj[a].add(Node(b, w))
        adj[b].add(Node(a, w))
    }

    var resA: IntArray = dijkstra(A, V, adj)
    var resB: IntArray = dijkstra(B, V, adj)

    var answer = 0
    for (i in H) answer += resA[i] + resB[i]
    println(answer)
}

fun dijkstra(start: Int, v: Int, adj: Array<MutableList<Node>>): IntArray {
    val memo = IntArray(v + 1) { Int.MAX_VALUE }
    val pq = PriorityQueue<Node>()

    memo[start] = 0
    pq.add(Node(start, 0))

    while (pq.isNotEmpty()) {
        val (u, uw) = pq.poll()
        if (memo[u] < uw) continue
        for ((v, vw) in adj[u]) {
            val nw = uw + vw
            if (memo[v] <= nw) continue
            memo[v] = nw
            pq.add(Node(v, nw))
        }
    }
    for (i in 1..v) if (memo[i] == Int.MAX_VALUE) memo[i] = -1
    return memo
}

data class Node(val n: Int, val w: Int) : Comparable<Node> {
    override fun compareTo(o: Node): Int = this.w - o.w
}