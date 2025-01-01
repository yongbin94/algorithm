import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_000_000;
	static int D, P;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		dp = new int[D + 1];
		dp[0] = INF;
		for (int p = 1; p <= P; p++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			for (int d = D; d >= l; d--) {
				dp[d] = Math.max(dp[d], Math.min(dp[d - l], c));
			}
		}
		System.out.println(dp[D]);
	}
}