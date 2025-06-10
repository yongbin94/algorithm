import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] A;
    static boolean[] used;
    static Stack<Integer> S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N];
        used = new boolean[N];
        S = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) A[n] = Long.parseLong(st.nextToken());
        for (int n = 0; n < N; n++) if (dfs(n)) break;
        StringBuilder sb = new StringBuilder();
        while(!S.isEmpty()) sb.append(A[S.pop()]).append(" ");
        System.out.println(sb);
    }

    private static boolean dfs(int i) {
        used[i] = true;
        S.push(i);
        if (S.size() == N) return true;
        long u = A[i];
        for (int n = 0; n < N; n++) {
            if (used[n]) continue;
            long v = A[n];
            if ((u % 2 == 0) && (u / 2 == v)) if (dfs(n)) return true;
            if (u * 3 == v) if (dfs(n)) return true;
        }
        used[i] = false;
        S.pop();
        return false;
    }
}