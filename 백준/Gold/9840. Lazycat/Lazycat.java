import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		Queue<Pos> q = new ArrayDeque<>();
		int idx = 0;
		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			for (int c = 0; c < N; c++) {
				char ch = input.charAt(c);
				if (ch == 'X') map[r][c] = -2;
				else if (ch == 'F') map[r][c] = idx++;
				else if (ch == 'B') map[r][c] = -3;
				else {
					if (ch == 'S') q.offer(new Pos(r, c, 0));
					map[r][c] = -1;
				}
			}
		}
		visited = new boolean[N][N][1 << idx];
		int time = 0;
		while (!q.isEmpty()) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				if (map[p.r][p.c] == -3) {
					if (Integer.bitCount(p.b) == idx) {
						System.out.println(time);
						return;
					}
					continue;
				}
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (!isIn(nr, nc) || map[nr][nc] == -2) continue;
					int nb = p.b | (map[nr][nc] < 0 ? 0 : (1 << map[nr][nc]));
					if (visited[nr][nc][p.b]) continue;
					visited[nr][nc][nb] = true;
					q.offer(new Pos(nr, nc, nb));
				}
			}
			time++;
		}
		System.out.println(-1);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static class Pos {
		int r, c, b;

		public Pos(int r, int c, int b) {
			this.r = r;
			this.c = c;
			this.b = b;
		}
	}
}