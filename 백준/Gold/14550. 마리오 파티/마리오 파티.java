import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if (N == 0) {
				System.out.println(sb);
				return;
			}
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			int[] A = new int[N + 1];
			int i = 1;
			while (i <= N) {
				st = new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) {
					A[i++] = Integer.parseInt(st.nextToken());
				}
			}
			int[] dp = new int[N + 2];
			Arrays.fill(dp, Integer.MIN_VALUE);
			dp[0] = 0;
			while (T-- > 0) {
				for (i = N + 1; i > 0; i--) {
					for (int s = 1; s <= S; s++) {
						if (i - s < 0) break;
						if (dp[i - s] == Integer.MIN_VALUE) continue;
						dp[i] = Math.max(dp[i], dp[i - s] + A[i - s]);
					}
				}
			}
			sb.append(dp[N + 1]).append("\n");
		}
	}
}
