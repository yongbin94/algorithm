import java.util.*;

public class Main {
	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt();
		long[] dp = new long[N + 1];
		dp[1] = 1;
		if (N > 1) dp[2] = 2;
		for (int n = 3; n <= N; n++) {
			dp[n] = Math.max(dp[n], dp[n - 1] + 1);
			for (int i = 0; n + i <= N; i++) {
				dp[n + i] = Math.max(dp[n + i], dp[n - 3] * (i + 2));
			}
		}
		System.out.println(dp[N]);
	}
}
