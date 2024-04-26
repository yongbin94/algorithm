import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		PriorityQueue<Corn> pq = new PriorityQueue<>();
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			visited[r][0] = true;
			pq.offer(new Corn(r, 0));
			if (M > 1) {
				visited[r][M - 1] = true;
				pq.offer(new Corn(r, M - 1));
			}
		}
		for (int c = 1; c < M - 1; c++) {
			visited[0][c] = true;
			pq.offer(new Corn(0, c));
			if (N > 1) {
				visited[N - 1][c] = true;
				pq.offer(new Corn(N - 1, c));
			}
		}
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (K-- > 0) {
			Corn corn = pq.poll();
			sb.append(corn.r + 1).append(" ").append(corn.c + 1).append("\n");
			for (int d = 0; d < 4; d++) {
				int nr = corn.r + dr[d];
				int nc = corn.c + dc[d];
				if (!isIn(nr, nc) || visited[nr][nc])
					continue;
				visited[nr][nc] = true;
				pq.offer(new Corn(nr, nc));
			}
		}
		System.out.println(sb);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static class Corn implements Comparable<Corn> {
		int r, c;

		public Corn(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Corn o) {
			return map[o.r][o.c] - map[this.r][this.c];
		}
	}
}