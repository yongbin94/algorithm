fun main() {
    val G = readln().toInt()
    var curr = 2L
    var prev = 1L
    val res = mutableListOf<Long>()
    while (true) {
        val diff = curr * curr - prev * prev
        if (curr - prev == 1L && diff > G) break
        if (diff >= G) {
            if (diff == G.toLong()) res.add(curr)
            prev++
        } else {
            curr++
        }
    }
    println(if (res.isEmpty()) "-1" else res.joinToString("\n"))
}