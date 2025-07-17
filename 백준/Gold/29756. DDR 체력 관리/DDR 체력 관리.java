import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][101];
		for (int n = 0; n <= N; n++) {
			Arrays.fill(dp[n], -1);
		}
		dp[0][100] = 0;
		StringTokenizer S = new StringTokenizer(br.readLine());
		StringTokenizer H = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			int s = Integer.parseInt(S.nextToken());
			int h = Integer.parseInt(H.nextToken());
			for (int i = 0; i <= 100; i++) {
				int score = dp[n - 1][i];
				if (score == -1) continue;
				int hp = Math.min(100, i + K);
				dp[n][hp] = Math.max(dp[n][hp], score);
				if (hp - h >= 0) dp[n][hp - h] = Math.max(dp[n][hp - h], score + s);
			}
		}
		System.out.println(Arrays.stream(dp[N]).max().getAsInt());
	}
}