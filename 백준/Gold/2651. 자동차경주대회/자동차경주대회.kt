import java.util.*

fun main() {
    val MAX = readln().toInt()
    val N = readln().toInt()
    val A = IntArray(N + 2)
    val B = IntArray(N + 2)
    val dp = LongArray(N + 2) { 100_000_000_000L }
    val prev = IntArray(N + 2) { -1 }
    dp[0] = 0
    var st = StringTokenizer(readln())
    for (i in 1..N + 1) A[i] = st.nextToken().toInt()
    st = StringTokenizer(readln())
    for (i in 1..N) B[i] = st.nextToken().toInt()

    for (i in 0..N) {
        var move = 0L
        for (j in i + 1..N + 1) {
            move += A[j]
            if (move > MAX) break
            if (dp[j] > dp[i] + B[j]) {
                dp[j] = dp[i] + B[j]
                prev[j] = i
            }
        }
    }
    var i = prev[N + 1]
    val stack = ArrayDeque<Int>()
    while (i != 0) {
        stack.push(i)
        i = prev[i]
    }
    val res = StringBuilder().append("${dp[N + 1]}\n${stack.size}\n")
    while (stack.isNotEmpty()) res.append("${stack.pop()} ")
    println(res)
}