import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final long INF = Long.MAX_VALUE >> 1;
		int N = Integer.parseInt(br.readLine());
		String S = br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] dp = new long[5];
		Arrays.fill(dp, INF);
		for (char ch : S.toCharArray()) {
			int v = Integer.parseInt(st.nextToken());
			if (ch == 'U') dp[0] = Math.min(dp[0], v);
			else if (ch == 'O') dp[1] = Math.min(dp[1], dp[0] + v);
			else if (ch == 'S') dp[2] = Math.min(dp[2], dp[1] + v);
			else if (ch == 'P') dp[3] = Math.min(dp[3], dp[2] + v);
			else if (ch == 'C') dp[4] = Math.min(dp[4], dp[3] + v);
		}
		System.out.println(dp[4] == INF ? -1 : dp[4]);
	}
}