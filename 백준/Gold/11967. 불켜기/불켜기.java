import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Pos> list[][];
	static boolean[][] visited;
	static boolean[][] light;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N][N];
		for (int r = 0; r < N; r++)
			for (int c = 0; c < N; c++)
				list[r][c] = new ArrayList<>();
		visited = new boolean[N][N];
		light = new boolean[N][N];
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			list[r][c].add(new Pos(a, b));
		}
		solution();
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void solution() {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(0, 0));
		visited[0][0] = true;
		light[0][0] = true;
		while (!q.isEmpty()) {
			Pos p = q.poll();
			for (Pos t : list[p.r][p.c]) {
				if (visited[t.r][t.c])
					continue;
				light[t.r][t.c] = true;
				for (int d = 0; d < 4; d++) {
					int nr = t.r + dr[d];
					int nc = t.c + dc[d];
					if (!isIn(nr, nc) || !visited[nr][nc])
						continue;
					visited[t.r][t.c] = true;
					q.offer(t);
					break;
				}
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (!isIn(nr, nc) || visited[nr][nc] || !light[nr][nc])
					continue;
				visited[nr][nc] = true;
				q.offer(new Pos(nr, nc));
			}
		}

		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (light[r][c])
					answer++;
			}
		}
		System.out.println(answer);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
