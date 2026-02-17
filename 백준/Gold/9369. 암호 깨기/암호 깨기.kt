fun main() {
    val T = readln().toInt()
    val res = StringBuilder()
    repeat(T) {
        val N = readln().toInt()
        val S = Array(N) { readln().toCharArray() }
        val O = readln().toCharArray()
        val A = Array(N) { IntArray(26) { -1 } }
        val B = Array(N) { IntArray(26) { -1 } }
        val used = BooleanArray(N) { true }
        for (i in 0 until N) {
            if (S[i].size != O.size) {
                used[i] = false
                continue
            }
            for (j in 0 until O.size) {
                val a = S[i][j] - 'a'
                val b = O[j] - 'a'
                if ((A[i][a] != -1 && A[i][a] != b) || (B[i][b] != -1 && B[i][b] != a)) {
                    used[i] = false
                    break
                }
                A[i][a] = b
                B[i][b] = a
            }
        }

        if (!used.contains(true)) {
            readln()
            res.append("IMPOSSIBLE\n")
        } else {
            for (i in 0 until N) {
                if (!used[i]) continue
                var count = 0
                val checkA = BooleanArray(26)
                val checkB = BooleanArray(26)
                for (j in 0 until 26) {
                    if (A[i][j] != -1) {
                        checkA[j] = true
                        checkB[A[i][j]] = true
                        count++
                    }
                }

                var a = -1
                var b = -1
                if (count == 25) {
                    for (j in 0 until 26) if (!checkA[j]) a = j
                    for (j in 0 until 26) if (!checkB[j]) b = j
                    A[i][a] = b
                    B[i][b] = a
                }
            }

            for (ch in readln().toCharArray()) {
                val a = ch - 'a'
                var t = -2
                for (i in 0 until N) {
                    if (!used[i]) continue
                    if (t == -2) {
                        t = A[i][a]
                    } else if (t != A[i][a]) {
                        t = -1
                        break
                    }
                }
                res.append(if (t < 0) "?" else (t + 'a'.code).toChar())
            }
            res.append("\n")
        }
    }
    println(res)
}