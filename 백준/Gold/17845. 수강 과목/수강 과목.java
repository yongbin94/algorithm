import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dp = new int[N + 1];
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			for (int n = N; n >= t; n--) {
				dp[n] = Math.max(dp[n], dp[n - t] + i);
			}
		}
		System.out.println(dp[N]);
	}
}