import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A, in;
    static ArrayList<Integer>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
        }
        in = new int[N + 1];
        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
            in[a]++;
            in[b]++;
        }
        solution();
    }

    private static void solution() {
        int res = 0;
        int[][] dp = new int[2][N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        for (int n = 1; n <= N; n++) {
            if (in[n] == 1) {
                dp[0][n] = A[n];
                q.offer(n);
                visited[n] = true;
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            int sum1 = 0, sum2 = 0;
            for (int v : edges[u]) {
                if (visited[v]) {
                    sum1 += Math.max(dp[0][v], dp[1][v]);
                    sum2 += dp[1][v];
                    continue;
                }
                if (--in[v] == 1) {
                    q.offer(v);
                    visited[v] = true;
                }
            }
            dp[0][u] = sum2 + A[u];
            dp[1][u] = sum1;
            res = Math.max(res, dp[0][u]);
            res = Math.max(res, dp[1][u]);
        }
        System.out.println(res);
    }
}