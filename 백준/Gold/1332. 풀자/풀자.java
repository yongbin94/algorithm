import java.io.*;
import java.util.*;

public class Main {
    static int N, V, answer;
    static int[] P;
    static Set<Long> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        P = new int[N];
        answer = Integer.MAX_VALUE;
        visited = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            P[i] = Integer.parseInt(st.nextToken());

        recur(0, P[0], P[0], 1);
        System.out.println(Math.min(answer, N));
    }

    private static void recur(int i, int max, int min, int v) {
        Long n = (i * 10000L + max) * 10000 + min;
        if (visited.contains(n))
            return;
        visited.add(n);

        if (v >= answer)
            return;

        if (max - min >= V) {
            answer = v;
            return;
        }

        if (i + 2 < N)
            recur(i + 2, Math.max(max, P[i + 2]), Math.min(min, P[i + 2]), v + 1);
        if (i + 1 < N)
            recur(i + 1, Math.max(max, P[i + 1]), Math.min(min, P[i + 1]), v + 1);
    }
}