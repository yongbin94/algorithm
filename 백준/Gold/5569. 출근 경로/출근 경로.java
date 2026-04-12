import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int W = sc.nextInt();
		int H = sc.nextInt();

		int[][][][] dp = new int[W + 1][H + 1][2][2];
		for (int i = 2; i <= W; i++) {
			dp[i][1][0][0] = 1;
		}
		for (int i = 2; i <= H; i++) {
			dp[1][i][1][0] = 1;
		}

		for (int w = 2; w <= W; w++) {
			for (int h = 2; h <= H; h++) {
				dp[w][h][0][0] = (dp[w - 1][h][0][0] + dp[w - 1][h][0][1]) % 100000;
				dp[w][h][0][1] = dp[w - 1][h][1][0];

				dp[w][h][1][0] = (dp[w][h - 1][1][0] + dp[w][h - 1][1][1]) % 100000;
				dp[w][h][1][1] = dp[w][h - 1][0][0];
			}
		}

		int res = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				res = (res + dp[W][H][i][j]) % 100000;
			}
		}
		System.out.println(res);
	}
}