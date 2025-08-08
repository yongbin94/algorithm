import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int INF = 1_000_000;
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dp = new int[K + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int C = Integer.parseInt(st.nextToken());
			for (int k = K - C; k >= 0; k--) {
				if (dp[k] == INF) continue;
				dp[k + C] = Math.min(dp[k + C], dp[k] + 1);
			}
		}
		System.out.println(dp[K] == INF ? -1 : dp[K]);
	}
}
