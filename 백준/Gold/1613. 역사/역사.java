import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static boolean[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new boolean[N + 1][N + 1];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			A[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (!A[i][k]) continue;
				for (int j = 1; j <= N; j++) {
					if (!A[k][j]) continue;
					A[i][j] = true;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int S = Integer.parseInt(br.readLine());
		while (S-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(A[a][b] ? -1 : A[b][a] ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}
}
