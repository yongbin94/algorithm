import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static boolean[][] map;
	static int[][] A;
	static int[] B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		A = new int[N][M];
		B = new int[N * M / 2 + 1];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = st.nextToken().charAt(0) == '1';
			}
		}

		int idx = 1;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (!map[n][m]) continue;
				bfs(n, m, idx++);
			}
		}

		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (A[r][c] != 0)
					continue;
				int sum = 1;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					boolean flag = true;
					if (!isIn(nr, nc)) continue;
					for (int t = 0; t < d; t++) {
						int tr = r + dr[t];
						int tc = c + dc[t];
						if (!isIn(tr, tc)) continue;
						if (A[nr][nc] == A[tr][tc]) flag = false;
					}
					if (flag) sum += B[A[nr][nc]];
				}
				answer = Math.max(answer, sum);
			}
		}
		System.out.println(answer);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void bfs(int n, int m, int idx) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(n, m));
		A[n][m] = idx;
		map[n][m] = false;
		while (!q.isEmpty()) {
			Pos p = q.poll();
			B[idx]++;
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (!isIn(nr, nc) || !map[nr][nc]) continue;
				map[nr][nc] = false;
				A[nr][nc] = idx;
				q.offer(new Pos(nr, nc));
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
