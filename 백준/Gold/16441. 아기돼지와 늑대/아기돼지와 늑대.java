import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		Queue<Pos> q = new ArrayDeque<>();
		for (int n = 0; n < N; n++) {
			String input = br.readLine();
			for (int m = 0; m < M; m++) {
				char v = input.charAt(m);
				map[n][m] = v;
				if (v == '.') {
					map[n][m] = 'P';
				} else if (v == 'W') {
					q.offer(new Pos(n, m));
					visited[n][m] = true;
				}
			}
		}
		bfs(q);
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				sb.append(map[n][m]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void bfs(Queue<Pos> q) {
		while (!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				while (map[nr][nc] == '+' && map[nr + dr[d]][nc + dc[d]] != '#') {
					nr += dr[d];
					nc += dc[d];
				}
				if (map[nr][nc] == '#' || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				if (map[nr][nc] == 'P')
					map[nr][nc] = '.';
				q.offer(new Pos(nr, nc));
			}
		}
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}