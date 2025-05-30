import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100_000_000;
    static int N, M;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 2];
        dp = new int[N + 1][M + 2][3];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            map[n][0] = INF;
            map[n][M + 1] = INF;
            Arrays.stream(dp[n]).forEach(v -> Arrays.fill(v, INF));
            for (int m = 1; m <= M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
        System.out.println(Arrays.stream(dp[N]).flatMapToInt(Arrays::stream).min().getAsInt());
    }

    private static void solution() {
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (a == b) continue;
                        dp[n][m][a] = Math.min(dp[n][m][a], dp[n - 1][m + b - 1][b] + map[n][m]);
                    }
                }
            }
        }
    }
}