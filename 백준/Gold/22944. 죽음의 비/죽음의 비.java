import java.io.*;
import java.util.*;

public class Main {
	static int N, H, D;
	static int[][] visited;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		visited = new int[N][N];
		map = new char[N][];
		for (int n = 0; n < N; n++)
			map[n] = br.readLine().toCharArray();
		bfs();

	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		Pos S = find();
		visited[S.r][S.c] = H;
		q.offer(S);
		int time = 0;
		while (!q.isEmpty()) {
			time++;
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (!isIn(nr, nc))
						continue;
					if (map[nr][nc] == 'E') {
						System.out.println(time);
						return;
					}
					int nd = map[nr][nc] == 'U' ? D : p.d;
					int nh = nd == 0 ? p.h - 1 : p.h;
					nd = Math.max(nd - 1, 0);
					if (visited[nr][nc] >= nd + nh)
						continue;
					visited[nr][nc] = nd + nh;
					q.offer(new Pos(nr, nc, nh, nd));
				}
			}
		}
		System.out.println(-1);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static Pos find() {
		for (int r = 0; r < N; r++)
			for (int c = 0; c < N; c++)
				if (map[r][c] == 'S')
					return new Pos(r, c, H, 0);
		return null;
	}

	private static class Pos {
		int r, c, h, d;

		public Pos(int r, int c, int h, int d) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
			this.d = d;
		}
	}
}