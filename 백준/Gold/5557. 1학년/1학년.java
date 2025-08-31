import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N - 1][21];
		StringTokenizer st = new StringTokenizer(br.readLine());
		dp[0][Integer.parseInt(st.nextToken())] = 1;
		for (int n = 0; n < N - 2; n++) {
			int v = Integer.parseInt(st.nextToken());
			for (int i = 0; i <= 20; i++) {
				if (dp[n][i] == 0) continue;
				if (i + v <= 20) dp[n + 1][i + v] += dp[n][i];
				if (i - v >= 0) dp[n + 1][i - v] += dp[n][i];
			}
		}
		System.out.println(dp[N - 2][Integer.parseInt(st.nextToken())]);
	}
}