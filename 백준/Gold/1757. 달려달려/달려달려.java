import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][M + 1];
        for (int[] a : dp) Arrays.fill(a, -1);
        dp[0][0] = 0;
        for (int n = 1; n <= N; n++) {
            int d = Integer.parseInt(br.readLine());
            if (dp[n - 1][0] != -1) dp[n][0] = Math.max(dp[n][0], dp[n - 1][0]);
            for (int m = 1; m <= M; m++) {
                if (dp[n - 1][m - 1] == -1) continue;
                dp[n][m] = dp[n - 1][m - 1] + d;
                if (n + m <= N) dp[n + m][0] = Math.max(dp[n + m][0], dp[n][m]);
            }
        }
        System.out.println(dp[N][0]);
    }
}
