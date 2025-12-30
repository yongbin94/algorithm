import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] v = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N + 1][N + 1];

        for (int len = 1; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                int k = N - len + 1;

                if (i == j) dp[i][j] = v[i] * k;
                else dp[i][j] = Math.max(dp[i + 1][j] + v[i] * k, dp[i][j - 1] + v[j] * k);
            }
        }

        System.out.println(dp[1][N]);
    }
}