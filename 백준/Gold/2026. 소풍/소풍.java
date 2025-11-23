import java.io.*;
import java.util.*;

public class Main {
	static int K, N, F;
	static boolean[][] A;
	static int[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		A = new boolean[N + 1][N + 1];
		S = new int[K];
		for (int n = 1; n <= N; n++) {
			A[n][n] = true;
		}
		while (F-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			A[a][b] = true;
			A[b][a] = true;
		}
		boolean[] a = new boolean[N + 1];
		Arrays.fill(a, true);
		if (recur(1, 0, a)) return;
		System.out.println(-1);

	}

	private static boolean recur(int start, int depth, boolean[] a) {
		if (depth == K) {
			StringBuilder sb = new StringBuilder();
			for (int v : S) {
				sb.append(v).append("\n");
			}
			System.out.println(sb);
			return true;
		}
		for (int i = start; i <= N; i++) {
			int cnt = 0;
			boolean[] b = new boolean[N + 1];
			for (int j = 1; j <= N; j++) {
				if (b[j] = a[j] & A[i][j]) cnt++;
			}
			if (cnt < K) continue;
			S[depth] = i;
			if (recur(i + 1, depth + 1, b)) return true;
		}
		return false;
	}
}