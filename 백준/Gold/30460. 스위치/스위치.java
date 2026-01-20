import java.io.*;
import java.util.*;

public class Main {
	static final long INF = 1_000_000_000_000L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[] A = new long[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}

		long[] dp = new long[N + 1];
		Arrays.fill(dp, -INF);

		dp[0] = 0;
		for (int i = 0; i < N; i++) {
			dp[i + 1] = Math.max(dp[i + 1], dp[i] + A[i + 1]);

			long sum = 0;
			for (int k = 1; k <= 3; k++) {
				if (i + k <= N) sum += A[i + k];
			}
			if (i + 3 <= N) dp[i + 3] = Math.max(dp[i + 3], dp[i] + sum * 2);
			else dp[N] = Math.max(dp[N], dp[i] + sum * 2);

		}
		System.out.println(dp[N]);
	}
}
