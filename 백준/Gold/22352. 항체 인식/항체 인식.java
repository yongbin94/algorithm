import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] org;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		org = new int[N][M];
		for (int n = 0; n < N * 2; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				if (n < N)
					map[n][m] = Integer.parseInt(st.nextToken());
				else
					org[n - N][m] = Integer.parseInt(st.nextToken());
			}
		}
		solution();
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] != org[n][m]) {
					System.out.println("NO");
					return;
				}

			}
		}
		System.out.println("YES");
	}

	private static void solution() {
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] != org[n][m]) {
					bfs(n, m);
					return;
				}
			}
		}
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void bfs(int n, int m) {
		int a = map[n][m];
		int b = org[n][m];

		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(n, m));

		boolean[][] visited = new boolean[N][M];
		visited[n][m] = true;

		map[n][m] = b;
		while (!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (!isIn(nr, nc) || map[nr][nc] != a)
					continue;
				q.offer(new Pos(nr, nc));
				map[nr][nc] = b;
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}