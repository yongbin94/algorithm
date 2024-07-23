import java.io.*;
import java.util.*;

public class Main {
    static int A, B, C;
    static TreeSet<Integer> answer;
    static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        answer = new TreeSet<>();
        checked = new boolean[A + 1][B + 1];
        recur(0, 0, C);
        StringBuilder sb = new StringBuilder();
        for (int v : answer)
            sb.append(v).append(" ");
        System.out.println(sb);
    }

    private static void recur(int a, int b, int c) {
        if (checked[a][b])
            return;
        checked[a][b] = true;
        if (a == 0)
            answer.add(c);
        if (a != 0) {
            if (b != B) {
                int v = Math.min(a, B - b);
                recur(a - v, b + v, c);
            }
            if (c != C) {
                int v = Math.min(a, C - c);
                recur(a - v, b, c + v);
            }
        }
        if (b != 0) {
            if (a != A) {
                int v = Math.min(b, A - a);
                recur(a + v, b - v, c);
            }
            if (c != C) {
                int v = Math.min(b, C - c);
                recur(a, b - v, c + v);
            }
        }
        if (c != 0) {
            if (b != B) {
                int v = Math.min(c, B - b);
                recur(a, b + v, c - v);
            }
            if (a != A) {
                int v = Math.min(c, A - a);
                recur(a + v, b, c - v);
            }
        }
    }
}