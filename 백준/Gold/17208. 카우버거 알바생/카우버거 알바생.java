import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[M + 1][K + 1];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int i = M; i >= x; i--) {
				for (int j = K; j >= y; j--) {
					if (dp[i - x][j - y] + 1 > dp[i][j]) {
						dp[i][j] = dp[i - x][j - y] + 1;
					}
				}
			}
		}

		System.out.println(dp[M][K]);
	}
}
