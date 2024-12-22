import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
    static int[] C, E;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        C = new int[N];
        E = new int[N];
        map = new int[N][N];
        dp = new int[N][T + 1];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            C[n] = Integer.parseInt(st.nextToken());
            E[n] = Integer.parseInt(st.nextToken());
        }

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++)
                map[r][c] = Integer.parseInt(st.nextToken());
        }

        for (int t = 1; t <= T; t++) {
            for (int n = 0; n < N; n++) {
                if(dp[n][t - 1] >= C[n])
                    dp[n][t] = dp[n][t - 1] + E[n];
                for (int m = 0; m < N; m++) {
                    if (t >= map[n][m] && dp[m][t - map[n][m]] >= C[n])
                        dp[n][t] = Math.max(dp[n][t], dp[m][t - map[n][m]]);
                }
            }
        }
        int answer = 0;
        for(int n = 0; n < N; n++)
            answer = Math.max(answer, dp[n][T]);
        System.out.println(answer);
    }
}