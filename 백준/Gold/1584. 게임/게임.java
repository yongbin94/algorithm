import java.io.*;
import java.util.*;

public class Main {
	static int[][] map = new int[501][501];
	static int[][] dist = new int[501][501];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			fill(new StringTokenizer(br.readLine()), 1);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			fill(new StringTokenizer(br.readLine()), 2);
		}

		System.out.println(dijkstra());
	}

	private static void fill(StringTokenizer st, int val) {
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());

		int sx = Math.min(x1, x2);
		int ex = Math.max(x1, x2);
		int sy = Math.min(y1, y2);
		int ey = Math.max(y1, y2);

		for (int i = sx; i <= ex; i++) {
			for (int j = sy; j <= ey; j++) {
				if (map[i][j] < val) map[i][j] = val;
			}
		}
	}

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	private static int dijkstra() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		Arrays.stream(dist).forEach(v -> Arrays.fill(v, Integer.MAX_VALUE));
		dist[0][0] = 0;
		pq.offer(new Pos(0, 0, 0));

		while (!pq.isEmpty()) {
			Pos p = pq.poll();

			if (p.x == 500 && p.y == 500) return p.d;
			if (dist[p.x][p.y] < p.d) continue;

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (!isIn(nx, ny) || map[nx][ny] == 2) continue;
				int nd = p.d + map[nx][ny];
				if (dist[nx][ny] <= nd) continue;
				dist[nx][ny] = nd;
				pq.offer(new Pos(nx, ny, nd));
			}
		}
		return -1;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x <= 500 && y >= 0 && y <= 500;
	}

	private static class Pos implements Comparable<Pos> {
		int x, y, d;

		public Pos(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Pos o) {
			return this.d - o.d;
		}
	}
}