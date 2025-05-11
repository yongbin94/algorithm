import java.io.*;
import java.util.*;

public class Main {
	static int N, K, X;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		boolean[][] dp = new boolean[K + 1][K * X + 1];
		dp[0][0] = true;
		int answer = 0;
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			for(int k = K - 1; k >= 0; k--) {
				for(int i = 0; i + a <= K * X; i++) {
					if(dp[k][i]) {
						dp[k + 1][i + a] = true;
						if(k == K - 1) answer = Math.max(answer, (i + a)* (K * X - (i + a)));
					}
				}
			}
			
		}
		System.out.println(answer);
	}
}