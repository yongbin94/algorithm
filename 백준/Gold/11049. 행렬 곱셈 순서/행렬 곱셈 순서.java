import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] A = new int[N][2];
		int[][] dp = new int[N][N];
        
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i][0] = Integer.parseInt(st.nextToken());
			A[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int n = 1; n < N; n++) {
			for (int i = 0; i + n < N; i++) {
				int j = i + n;
				dp[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {
					int v = dp[i][k] + dp[k + 1][j] + (A[i][0] * A[k][1] * A[j][1]);
					dp[i][j] = Math.min(dp[i][j], v);
				}
			}
		}

		System.out.println(dp[0][N - 1]);
	}
}