import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			long[] dp = new long[M + 1];
			Arrays.fill(dp, 1);
			dp[0] = 0;
			while (N-- > 1) {
				for (int m = M; m > 0; m--) {
					dp[m] = 0;
					for (int i = m / 2; i > 0; i--) {
						dp[m] += dp[i];
					}
				}
			}
			long res = 0;
			for (int m = 1; m <= M; m++) {
				res += dp[m];
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
}
