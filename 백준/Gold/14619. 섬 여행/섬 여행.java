import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][501];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            Arrays.fill(dp[n], Integer.MAX_VALUE);
            dp[n][0] = Integer.parseInt(st.nextToken());
        }
        List<Integer>[] edges = new ArrayList[N + 1];
        Arrays.setAll(edges, v -> new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        for (int k = 1; k <= 500; k++) {
            for (int n = 1; n <= N; n++) {
                for (int v : edges[n]) {
                    dp[n][k] = Math.min(dp[n][k], dp[v][k - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            sb.append(dp[A][K] == Integer.MAX_VALUE ? -1 : dp[A][K]).append("\n");
        }
        System.out.println(sb);
    }
}