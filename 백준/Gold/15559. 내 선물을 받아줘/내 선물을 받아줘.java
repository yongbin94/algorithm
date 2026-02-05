import java.io.*;
import java.util.*;

public class Main {
	static int N, M, idx;
	static char[][] map;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		A = new int[N][M];
		for (int n = 0; n < N; n++) {
			map[n] = br.readLine().toCharArray();
		}
		idx = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (A[n][m] != 0) continue;
				dfs(n, m);
			}
		}
		System.out.println(idx);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static int dfs(int n, int m) {
		int d = "NESW".indexOf(map[n][m]);
		int nr = n + dr[d];
		int nc = m + dc[d];
		if (!isIn(nr, nc) || A[nr][nc] == -1) return A[n][m] = ++idx;
		if (A[nr][nc] != 0) return A[n][m] = A[nr][nc];
		A[n][m] = -1;
		return A[n][m] = dfs(nr, nc);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
