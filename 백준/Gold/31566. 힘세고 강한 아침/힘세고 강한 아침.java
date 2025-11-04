import java.io.*;
import java.util.*;

public class Main {
    static int N, M, Q;
    static int[][][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1][N + 1];
        Arrays.stream(dist).forEach(a -> Arrays.stream(a).forEach(b -> Arrays.fill(b, 100_000_000)));
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for (int k = 1; k <= N; k++) {
                if (b == k || t == k) continue;
                dist[b][k][t] = c;
            }
        }
        for (int k = 1; k <= N; k++) {
            for (int c = 1; c <= N; c++) {
                if (c == k) continue;
                for (int a = 1; a <= N; a++) {
                    if (a == k) continue;
                    for (int b = 1; b <= N; b++) {
                        if (b == k) continue;
                        dist[a][k][b] = Math.min(dist[a][k][b], dist[a][k][c] + dist[c][k][b]);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = dist[s][k][e];
            sb.append(v == 100_000_000 ? "No" : v).append("\n");
        }
        System.out.println(sb);
    }
}