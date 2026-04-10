import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int o1 = Integer.parseInt(st.nextToken());
		int o2 = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		int[][][] dp = new int[T + 1][N + 1][N + 1];
		for (int[][] d2 : dp) for (int[] d1 : d2) Arrays.fill(d1, 999999);
		dp[0][o1][o2] = 0;

		for (int t = 1; t <= T; t++) {
			int v = Integer.parseInt(br.readLine());
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dp[t - 1][i][j] == 999999) continue;
					int curr = dp[t - 1][i][j];
					dp[t][v][j] = Math.min(dp[t][v][j], curr + Math.abs(v - i));
					dp[t][i][v] = Math.min(dp[t][i][v], curr + Math.abs(v - j));
				}
			}
		}

		int res = 999999;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				res = Math.min(res, dp[T][i][j]);
			}
		}
		System.out.println(res);
	}
}