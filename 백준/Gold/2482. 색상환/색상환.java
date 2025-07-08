import java.util.*;

public class Main {
    static final int MOD = 1_000_000_003;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] dp = new int[N + 1][K + 1];
        dp[1][1] = 1;
        for (int n = 0; n <= N; n++) {
            dp[n][0] = 1;
            for (int k = 1, len = Math.min(n / 2, K); k <= len; k++) {
                dp[n][k] = (dp[n - 1][k] + dp[n - 2][k - 1]) % MOD;
            }
        }
        System.out.println(dp[N][K]);
    }
}