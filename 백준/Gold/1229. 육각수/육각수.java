import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
        int N = new Scanner(System.in).nextInt();

        int[] dp = new int[N + 1];
        Arrays.fill(dp, N);
        dp[0] = 0;

        for (int n = 1; ; n++) {
            int t = n * (2 * n - 1);
            if (t > N) break;

            for (int i = t; i <= N; i++) {
                if (dp[i - t] + 1 < dp[i]) {
                    dp[i] = dp[i - t] + 1;
                }
            }
        }
        System.out.println(dp[N]);
    }
}
