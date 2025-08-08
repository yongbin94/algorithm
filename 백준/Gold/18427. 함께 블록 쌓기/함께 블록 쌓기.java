import java.io.*;
import java.util.*;

public class Main {
	static int N, M, H;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		dp = new int[H + 1];
		dp[0] = 1;
		while (N-- > 0) {
			int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int h = H - 1; h >= 0; h--) {
				if (dp[h] == 0) continue;
				for (int a : A) {
					if (h + a > H) continue;
					dp[h + a] += dp[h];
					dp[h + a] %= 10007;
				}
			}
		}
		System.out.println(dp[H]);
	}
}