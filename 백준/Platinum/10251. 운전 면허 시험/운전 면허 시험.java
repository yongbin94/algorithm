
import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static int N, M, L, G;
    static int[][] rMap, cMap;
    static int[][][][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            memo = new int[2][N][M][N + M];
            rMap = new int[N][M - 1];
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M - 1; m++)
                    rMap[n][m] = Integer.parseInt(st.nextToken());
                for (int m = 0; m < M; m++) {
                    Arrays.fill(memo[0][n][m], INF);
                    Arrays.fill(memo[1][n][m], INF);
                }
            }
            cMap = new int[N - 1][M];
            for (int n = 0; n < N - 1; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++)
                    cMap[n][m] = Integer.parseInt(st.nextToken());
            }
            sb.append(solution()).append("\n");
        }
        System.out.println(sb);
    }

    private static int solution() {
        memo[0][0][0][0] = memo[1][0][0][0] = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int k = 0; k <= r + c; k++) {
                    if (c < M - 1) {
                        memo[0][r][c + 1][k] = Math.min(memo[0][r][c + 1][k], memo[0][r][c][k] + rMap[r][c]);
                        memo[0][r][c + 1][k + 1] = Math.min(memo[0][r][c + 1][k + 1], memo[1][r][c][k] + rMap[r][c]);

                    }
                    if (r < N - 1) {
                        memo[1][r + 1][c][k] = Math.min(memo[1][r + 1][c][k], memo[1][r][c][k] + cMap[r][c]);
                        memo[1][r + 1][c][k + 1] = Math.min(memo[1][r + 1][c][k + 1], memo[0][r][c][k] + cMap[r][c]);

                    }
                }
            }
        }

        int minTime = INF;
        for (int k = 0; k < N + M; k++) {
            if (memo[0][N - 1][M - 1][k] <= G || memo[1][N - 1][M - 1][k] <= G) {
                minTime = Math.min(minTime, (N + M - 2) * L + k);
            }
        }
        return minTime == INF ? -1 : minTime;
    }
}