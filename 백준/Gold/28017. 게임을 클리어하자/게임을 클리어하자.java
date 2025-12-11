import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N + 1][M];
		Arrays.stream(dp).forEach(v -> Arrays.fill(v, Integer.MAX_VALUE));
		Arrays.fill(dp[0], 0);
		int prev1 = 0, prev2 = 0;
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int curr1 = Integer.MAX_VALUE, curr2 = Integer.MAX_VALUE;
			for (int m = 0; m < M; m++) {
				int v = Integer.parseInt(st.nextToken());
				dp[n][m] = Math.min(dp[n][m], v + (dp[n - 1][m] == prev1 ? prev2 : prev1));
				if (dp[n][m] < curr1) {
					curr2 = curr1;
					curr1 = dp[n][m];
				} else if (dp[n][m] < curr2) {
					curr2 = dp[n][m];
				}
			}
			prev1 = curr1;
			prev2 = curr2;
		}
		System.out.println(Arrays.stream(dp[N]).min().getAsInt());
	}
}