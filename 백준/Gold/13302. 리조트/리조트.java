import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;
	static int N, M;
	static int[][] dp;
	static boolean[] closed;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		closed = new boolean[N + 1];
        if (M > 0) st = new StringTokenizer(br.readLine());
		while (M-- > 0) {
			closed[Integer.parseInt(st.nextToken())] = true;
		}
		dp = new int[N + 1][50];
		Arrays.stream(dp).forEach(v -> Arrays.fill(v, INF));
		dp[0][0] = 0;
		
		for (int n = 1; n <= N; n++) {
			for (int c = 0; c <= 40; c++) {
				if (closed[n]) {
					dp[n][c] = dp[n - 1][c];
					continue;
				}
				// 쿠폰 사용
				dp[n][c] = Math.min(dp[n][c], dp[n - 1][c + 3]);

				// 하루권 구매
				dp[n][c] = Math.min(dp[n][c], dp[n - 1][c] + 10000);

				// 3일권 구매
				for (int d = 0; d < 3; d++) {
					if (n + d > N) break;
					dp[n + d][c + 1] = Math.min(dp[n + d][c + 1], dp[n - 1][c] + 25000);
				}

				// 5일권 구매
				for (int d = 0; d < 5; d++) {
					if (n + d > N) break;
					dp[n + d][c + 2] = Math.min(dp[n + d][c + 2], dp[n - 1][c] + 37000);
				}
			}
		}
		System.out.println(Arrays.stream(dp[N]).min().getAsInt());
	}
}