import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val W = st.nextToken().toLong()
    val A = IntArray(N + 1)

    repeat(N - 1) {
        st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        A[u]++
        A[v]++
    }

    var cnt = 0
    for (i in 2..N) {
        if (A[i] == 1) {
            cnt++
        }
    }

    println(W.toDouble() / cnt)
}