import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String A = br.readLine();
		String B = br.readLine();

		int L1 = A.length();
		int L2 = B.length();

		int[][] dp = new int[L1 + 1][L2 + 1];

		for (int i = 0; i <= L1; i++) dp[i][0] = i;
		for (int j = 0; j <= L2; j++) dp[0][j] = j;

		for (int i = 1; i <= L1; i++) {
			for (int j = 1; j <= L2; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				}
			}
		}
		System.out.println(dp[L1][L2]);
	}
}