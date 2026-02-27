import java.util.*

fun main() {
    val st = StringTokenizer(readln())
    var X = st.nextToken().toLong()
    var K = st.nextToken().toLong()
    var i = 1L
    var res = 0L

    while (K > 0) {
        if (X % 2 == 0L) {
            if (K % 2 == 1L) res += i
            K /= 2
        }
        X /= 2
        i *= 2
    }
    println(res)
}