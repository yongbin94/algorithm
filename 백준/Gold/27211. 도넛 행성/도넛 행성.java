import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[N][M];
		boolean[][] visited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = st.nextToken().charAt(0) == '0';
			}
		}
		int res = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!map[r][c] || visited[r][c]) continue;
				Queue<Pos> q = new ArrayDeque<>();
				q.offer(new Pos(r, c));
				visited[r][c] = true;
				res++;
				while (!q.isEmpty()) {
					Pos p = q.poll();
					for (int d = 0; d < 4; d++) {
						int nr = (p.r + dr[d] + N) % N;
						int nc = (p.c + dc[d] + M) % M;
						if (!map[nr][nc] || visited[nr][nc]) continue;
						q.offer(new Pos(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
		System.out.println(res);
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
