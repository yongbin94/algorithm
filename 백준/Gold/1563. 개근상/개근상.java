import java.util.*;

public class Main {
	static int N;
	static int[][][] dp;
	static final int MOD = 1_000_000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		dp = new int[N + 1][2][3];
		dp[1][0][0] = 1;
		dp[1][0][1] = 1;
		dp[1][1][0] = 1;
		for (int n = 2; n <= N; n++) {
			dp[n][0][0] = Arrays.stream(dp[n - 1][0]).sum() % MOD;
			dp[n][0][1] = dp[n - 1][0][0];
			dp[n][0][2] = dp[n - 1][0][1];
			dp[n][1][0] = dp[n][0][0] + Arrays.stream(dp[n - 1][1]).sum() % MOD;
			dp[n][1][1] = dp[n - 1][1][0];
			dp[n][1][2] = dp[n - 1][1][1];
		}
		int answer = Arrays.stream(dp[N]).flatMapToInt(Arrays::stream).sum() % MOD;
		System.out.println(answer);
	}
}