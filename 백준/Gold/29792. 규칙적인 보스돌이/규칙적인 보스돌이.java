import java.io.*;
import java.util.*;

public class Main {
	static final int LMT = 15 * 60 + 1;
	static int N, M, K;
	static Long[] D;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = new Long[N];
		for (int n = 0; n < N; n++) {
			D[n] = Long.parseLong(br.readLine());
		}
		Arrays.sort(D, Comparator.reverseOrder());

		long[][] dp = new long[M][LMT];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			long p = Long.parseLong(st.nextToken());
			long q = Long.parseLong(st.nextToken());
			for (int m = 0; m < M; m++) {
				long v = (p + D[m] - 1) / D[m];
				if (v >= LMT) continue;
				for (int t = LMT - 1; t >= v; t--) {
					dp[m][t] = Math.max(dp[m][t], dp[m][(int)(t - v)] + q);
				}
			}
		}

		System.out.println(Arrays.stream(dp).mapToLong(v -> v[LMT - 1]).sum());
	}
}