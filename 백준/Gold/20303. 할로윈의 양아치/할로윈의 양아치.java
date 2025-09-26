import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[] C, parent, A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = new int[N + 1];
		parent = new int[N + 1];
		A = new int[N + 1];
		B = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			C[n] = Integer.parseInt(st.nextToken());
			parent[n] = n;
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		for (int n = 1; n <= N; n++) {
			A[find(n)] += C[n];
			B[find(n)]++;
		}

		int[] dp = new int[K];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		for (int n = 1; n <= N; n++) {
			if (B[n] == 0) continue;
			for (int k = K - B[n] - 1; k >= 0; k--) {
				if (dp[k] == -1) continue;
				dp[k + B[n]] = Math.max(dp[k + B[n]], dp[k] + A[n]);
			}
		}
		System.out.println(Arrays.stream(dp).max().getAsInt());
	}

	private static void union(int a, int b) {
		parent[find(b)] = find(a);
	}

	private static int find(int a) {
		if (a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
}