import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static int[][] memo;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][];
		memo = new int[N][M];
		visited = new boolean[N][M];
		for (int n = 0; n < N; n++)
			map[n] = br.readLine().toCharArray();
		solution();
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void solution() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0, 0));
		visited[0][0] = true;
		while (!pq.isEmpty()) {
			Pos p = pq.poll();
			if (p.r == N - 1 && p.c == M - 1) {
				System.out.println(memo[p.r][p.c]);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (!isIn(nr, nc) || visited[nr][nc])
					continue;
				visited[nr][nc] = true;
				memo[nr][nc] = memo[p.r][p.c] + (map[nr][nc] == '1' ? 1 : 0);
				pq.offer(new Pos(nr, nc));
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static class Pos implements Comparable<Pos> {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Pos o) {
			return memo[this.r][this.c] - memo[o.r][o.c];
		}

	}

}
