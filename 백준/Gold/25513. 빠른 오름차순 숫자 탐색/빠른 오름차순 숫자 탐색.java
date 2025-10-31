import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[5][5];
		for (int r = 0; r < 5; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 5; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int answer = recur(1, new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), 0);
		System.out.println(answer);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static int recur(int n, Pos pos, int cnt) {
		if (n == 7) return cnt;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);
		boolean[][] visited = new boolean[5][5];
		visited[pos.r][pos.c] = true;
		int time = 0;
		while (!q.isEmpty()) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				if (map[p.r][p.c] == n) return recur(n + 1, p, cnt + time);
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == -1) continue;
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}
			}
			time++;
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < 5 && c >= 0 && c < 5;
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}