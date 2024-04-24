import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[] pipe = { 0, 10, 5, 15, 3, 9, 12, 6, 15, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		int[] S = null;
		for (int n = 0; n < N; n++) {
			char[] A = br.readLine().toCharArray();
			for (int m = 0; m < M; m++) {
				char c = A[m];
				if (c == 'M')
					S = new int[] { n, m };
				map[n][m] = ".|-+1234MZ".indexOf(c);
			}
		}
		dfs(S[0], S[1]);
	}

	private static boolean dfs(int r, int c) {
		int v = map[r][c];
		visited[r][c] = true;
		if (v == 0) {
			int p = 0;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc) ||map[nr][nc] == 8)
					continue;
				if ((((1 << (d + 2)) % 15) & pipe[map[nr][nc]]) != 0)
					p += 1 << d;
			}
			if(p == 0)
				return false;
			StringBuilder sb = new StringBuilder();
			sb.append(r + 1).append(" ").append(c + 1).append(" ");
			for(int i = 1; i < 8; i++) {
				if(pipe[i] == p)
					sb.append(".|-+1234MZ".charAt(i));
			}
			System.out.println(sb);
			return true;
		}
		for (int d = 0; d < 4; d++) {
			if (((pipe[v] >> d) & 1) == 0)
				continue;
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc) || visited[nr][nc])
				continue;
			if (dfs(nr, nc))
				return true;
		}
		return false;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
