import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static int N, M;
    static int[][] map, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        res = new int[N + 1][N + 1];
        for (int n = 1; n <= N; n++) {
            Arrays.fill(map[n], INF);
            for (int m = 1; m <= N; m++) {
                if (n == m)
                    continue;
                res[n][m] = m;
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[a][b] = w;
            map[b][a] = w;
        }
        for (int k = 1; k <= N; k++) {
            for (int n = 1; n <= N; n++) {
                for (int m = 1; m <= N; m++) {
                    if (n == m)
                        continue;
                    if (map[n][m] > map[n][k] + map[k][m]) {
                        map[n][m] = map[n][k] + map[k][m];
                        res[n][m] = res[n][k];
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(i == j ? "-" : res[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}