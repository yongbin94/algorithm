import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static char[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][];
		visited = new boolean[N][M][K + 1];
		for (int n = 0; n < N; n++)
			map[n] = br.readLine().toCharArray();
		bfs();
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(0, 0, 0));
		visit(0, 0, 0);
		int count = 0;
		while (!q.isEmpty()) {
			count++;
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				if (p.r == N - 1 && p.c == M - 1) {
					System.out.println(count);
					return;
				}
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (!isIn(nr, nc))
						continue;
					if (map[nr][nc] == '1') {
						if (p.k < K && !visited[nr][nc][p.k + 1]) {
							visit(nr, nc, p.k + 1);
							q.offer(new Pos(nr, nc, p.k + 1));
						}
					} else {
						if (!visited[nr][nc][p.k]) {
							visit(nr, nc, p.k);
							q.offer(new Pos(nr, nc, p.k));
						}
					}
				}
			}
		}
		System.out.println(-1);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static void visit(int r, int c, int k) {
		for (int i = k; i <= K; i++)
			visited[r][c][k] = true;
	}

	private static class Pos {
		int r, c, k;

		public Pos(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
}