import java.util.*

fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val Q = st.nextToken().toInt()

    val backward = ArrayDeque<Int>()
    val forward = ArrayDeque<Int>()
    var current: Int? = null

    repeat(Q) {
        val line = readln()
        val cmd = line[0]

        when (cmd) {
            'B' -> if (backward.isNotEmpty()) {
                forward.addLast(current)
                current = backward.removeLast()
            }
            'F' -> if (forward.isNotEmpty()) {
                backward.addLast(current)
                current = forward.removeLast()
            }
            'A' -> {
                val next = line.substring(2).toInt()
                forward.clear()
                if (current != null) backward.addLast(current)
                current = next
            }
            'C' -> {
                if (backward.isNotEmpty()) {
                    val tmp = ArrayDeque<Int>()
                    var prev = -1
                    while (backward.isNotEmpty()) {
                        val curr = backward.removeFirst()
                        if (curr != prev) tmp.addLast(curr)
                        prev = curr
                    }
                    backward.clear()
                    backward.addAll(tmp)
                }
            }
        }
    }

    println(current)
    println(if (backward.isEmpty()) "-1" else backward.toList().reversed().joinToString(" "))
    println(if (forward.isEmpty()) "-1" else forward.toList().reversed().joinToString(" "))
}