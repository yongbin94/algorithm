import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N + 1][N + 1];
        Arrays.stream(dist).forEach(v -> Arrays.fill(v, INF));
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
        for (int k = 1; k <= N; k++) {
            dist[k][k] = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int res = INF, a = 0, b = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int v = 0;
                for (int n = 1; n <= N; n++) {
                    v += Math.min(dist[i][n], dist[j][n]);
                }
                if (res > v) {
                    res = v;
                    a = i;
                    b = j;
                }
            }
        }
        System.out.printf("%d %d %d", a, b, res * 2);
    }
}