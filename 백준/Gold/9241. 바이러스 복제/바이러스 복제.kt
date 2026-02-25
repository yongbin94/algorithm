fun main() {
    val s1 = readln()
    val s2 = readln()
    val n1 = s1.length
    val n2 = s2.length

    var l = 0
    while (l < n1 && l < n2 && s1[l] == s2[l]) {
        l++
    }
    if (l == n1 && n1 == n2) {
        println(0)
        return
    }

    var r1 = n1 - 1
    var r2 = n2 - 1
    while (r1 >= l && r2 >= l && s1[r1] == s2[r2]) {
        r1--
        r2--
    }

    println(if (r2 < l) 0 else r2 - l + 1)
}