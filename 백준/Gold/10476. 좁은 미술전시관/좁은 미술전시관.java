import java.io.*;
import java.util.*;

public class Main {
	static final int INF = -1_000_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][][] dp = new int[3][N + 1][K + 1];
		Arrays.stream(dp).forEach(a -> Arrays.stream(a).forEach(b -> Arrays.fill(b, INF)));

		if (K >= 1) {
			dp[0][1][1] = map[1][1];
			dp[1][1][1] = map[1][0];
		}
		dp[2][1][0] = map[1][0] + map[1][1];

		for (int n = 2; n <= N; n++) {
			for (int k = 0; k <= K; k++) {

				if (k >= 1) {
					dp[0][n][k] = Math.max(dp[0][n - 1][k - 1], dp[2][n - 1][k - 1]) + map[n][1];
					dp[1][n][k] = Math.max(dp[1][n - 1][k - 1], dp[2][n - 1][k - 1]) + map[n][0];
				}

				if (n != k) {
					dp[2][n][k] = Math.max(dp[0][n - 1][k], Math.max(dp[1][n - 1][k], dp[2][n - 1][k])) + map[n][0] + map[n][1];
				}
			}
		}
		System.out.print(Math.max(dp[0][N][K], Math.max(dp[1][N][K], dp[2][N][K])));
	}
}
