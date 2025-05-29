import java.io.*;
import java.util.*;

public class Main {
	static int N, M, H, W;
	static Pos S, F;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken()) + map[n - 1][m] + map[n][m - 1] - map[n - 1][m - 1];
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		S = new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		F = new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

		System.out.println(solution());

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static int solution() {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(S);
		boolean[][] visited = new boolean[N - H + 1][M - W + 1];
		visited[S.r][S.c] = true;
		for (int res = 0; !q.isEmpty(); res++) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos u = q.poll();
				if (u.r == F.r && u.c == F.c) return res;
				for (int d = 0; d < 4; d++) {
					int nr = u.r + dr[d];
					int nc = u.c + dc[d];
					if (!isIn(nr, nc) || visited[nr][nc] || !check(nr, nc)) continue;
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}

			}
		}
		return -1;
	}

	private static boolean check(int r, int c) {
		return map[r + H][c + W] - map[r][c + W] - map[r + H][c] + map[r][c] == 0;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N - H + 1 && c >= 0 && c < M - W + 1;
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
