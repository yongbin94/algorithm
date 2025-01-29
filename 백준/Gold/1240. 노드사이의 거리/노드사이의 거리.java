import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;
	static int N, M;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N + 1][N + 1];
		Arrays.stream(A).forEach(v -> Arrays.fill(v, INF));
		for (int n = 0; n < N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			A[a][b] = c;
			A[b][a] = c;
		}
		for (int k = 1; k <= N; k++)
			for (int n = 1; n <= N; n++)
				for (int m = 1; m <= N; m++)
					A[n][m] = Math.min(A[n][m], A[n][k] + A[k][m]);

		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			sb.append(A[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.println(sb);
	}
}