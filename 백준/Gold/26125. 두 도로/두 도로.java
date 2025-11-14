import java.io.*;
import java.util.*;

public class Main {
    static final long INF = 100_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        long[][] dist = new long[N + 1][N + 1];
        Arrays.stream(dist).forEach(v -> Arrays.fill(v, INF));
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            dist[u][v] = Math.min(dist[u][v], w);
        }
        for (int k = 1; k <= N; k++) {
            dist[k][k] = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int b1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            long res = dist[S][T];
            res = Math.min(res, dist[S][a1] + c1 + dist[b1][T]);
            res = Math.min(res, dist[S][a2] + c2 + dist[b2][T]);
            res = Math.min(res, dist[S][a1] + c1 + dist[b1][a2] + c2 + dist[b2][T]);
            res = Math.min(res, dist[S][a2] + c2 + dist[b2][a1] + c1 + dist[b1][T]);
            sb.append(res == INF ? -1 : res).append("\n");
        }
        System.out.println(sb);
    }
}