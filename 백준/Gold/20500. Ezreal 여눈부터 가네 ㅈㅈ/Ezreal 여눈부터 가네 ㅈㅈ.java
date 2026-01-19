import java.util.*;

public class Main {
	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		int N = new Scanner(System.in).nextInt();

		if (N == 1) {
			System.out.println(0);
			return;
		}

		long[] dp = new long[3];
		dp[1] = 1;
		dp[2] = 1;

		for (int i = 2; i < N; i++) {
			long[] next = new long[3];
			for (int j = 0; j < 3; j++) {
				if (dp[j] == 0) continue;

				int a = (j + 1) % 3;
				int b = (j + 5) % 3;

				next[a] = (next[a] + dp[j]) % MOD;
				next[b] = (next[b] + dp[j]) % MOD;
			}
			dp = next;
		}
		System.out.println(dp[1]);
	}
}