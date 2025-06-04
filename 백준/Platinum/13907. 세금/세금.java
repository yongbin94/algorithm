import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int N, M, K, S, D;
    static List<Edge>[] edges;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        dp = new int[N + 1][N + 1];
        Arrays.stream(dp).forEach(v -> Arrays.fill(v, INF));
        dp[S][0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int u = 1; u <= N; u++) {
                for (Edge v : edges[u]) {
                    dp[v.v][i] = Math.min(dp[v.v][i], dp[u][i - 1] + v.w);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.stream((dp[D])).min().getAsInt()).append("\n");
        int p = 0;
        while (K-- > 0) {
            p += Integer.parseInt(br.readLine());
            int min = INF;
            for (int i = 1; i <= N; i++) {
                min = Math.min(min, dp[D][i] + i * p);
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }
    
    private static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}