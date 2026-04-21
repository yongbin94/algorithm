import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[] dp = new long[N + 1];
		dp[0] = 1;
		dp[1] = 2;
		if (N >= 2) dp[2] = 7;

		long s = 0;
		for (int i = 3; i <= N; i++) {
			s = (s + dp[i - 3]) % MOD;
			dp[i] = (2 * dp[i - 1] + 3 * dp[i - 2] + 2 * s) % MOD;
		}

		System.out.println(dp[N]);
	}
}