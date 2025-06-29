import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[] dp = new int[X + 1];
		dp[0] = 1;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			for (int x = X; x >= 0; x--) {
				for (int c = 1; c <= C; c++) {
					if (L * c > x) break;
					dp[x] += dp[x - L * c];
				}
			}
		}
		System.out.println(dp[X]);
	}
}