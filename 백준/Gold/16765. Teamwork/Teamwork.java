import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] A = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			A[n] = Integer.parseInt(br.readLine());
		}
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int max = 0;
			for (int k = 1; k <= K; k++) {
				if (i - k < 0) break;
				max = Math.max(max, A[i - k + 1]);
				dp[i] = Math.max(dp[i], dp[i - k] + max * k);
			}
		}
		System.out.println(dp[N]);
	}
}
