import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] C;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = new int[2][N];
        dp = new int[M + 1];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            C[0][n] = Integer.parseInt(st.nextToken());
            C[1][n] = Integer.parseInt(st.nextToken());
        }
        for (int n = 0; n < N; n++)
            for (int m = M; m >= C[0][n]; m--)
                dp[m] = Math.max(dp[m], Math.max(dp[m - 1], dp[m - C[0][n]] + C[1][n]));

        System.out.println(dp[M]);
    }
}
