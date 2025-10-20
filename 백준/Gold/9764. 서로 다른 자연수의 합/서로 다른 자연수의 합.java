import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[2001];
		dp[0] = 1;
		for (int n = 1; n <= 2000; n++) {
			for (int m = 2000 - n; m >= 0; m--) {
				dp[n + m] += dp[m];
				dp[n + m] %= 100999;
			}
		}
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
		}
		System.out.println(sb);
	}
}
