import java.io.*;
import java.util.*;

public class Main {
	static int N, M, A, B, K;
	static Pos S, E;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]++;
		}
		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				map[n][m] += map[n - 1][m] + map[n][m - 1] - map[n - 1][m - 1];
			}
		}
		S = new Pos(new StringTokenizer(br.readLine()));
		E = new Pos(new StringTokenizer(br.readLine()));
		
		System.out.println(solution());
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static int solution() {
		boolean[][] visited = new boolean[N + 1][M + 1];
		Queue<Pos> q = new ArrayDeque<>();
		visited[S.r][S.c] = true;
		q.offer(S);
		int cnt = 0;
		while (!q.isEmpty()) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				if (p.r == E.r && p.c == E.c) return cnt;
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (!isIn(nr, nc) || visited[nr][nc] || !check(nr, nc)) continue;
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}
			}
			cnt++;
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r > 0 && r <= N - A + 1 && c > 0 && c <= M - B + 1;
	}

	private static boolean check(int r, int c) {
		return map[--r][--c] + map[r + A][c + B] == map[r + A][c] + map[r][c + B];
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Pos(StringTokenizer st) {
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
		}
	}
}