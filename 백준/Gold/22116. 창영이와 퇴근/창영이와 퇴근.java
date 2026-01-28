import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] A;
	static int[][] D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		D = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				D[i][j] = Integer.MAX_VALUE;
			}
		}

		System.out.println(dijkstra());
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		D[0][0] = 0;
		pq.offer(new Node(0, 0, 0));

		while (!pq.isEmpty()) {
			Node n = pq.poll();

			if (n.v > D[n.r][n.c]) continue;
			if (n.r == N - 1 && n.c == N - 1) return n.v;

			for (int d = 0; d < 4; d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];

				if (!isIn(nr, nc)) continue;
				int diff = Math.abs(A[nr][nc] - A[n.r][n.c]);
				int max = Math.max(n.v, diff);

				if (D[nr][nc] > max) {
					D[nr][nc] = max;
					pq.offer(new Node(nr, nc, D[nr][nc]));
				}
			}
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static class Node implements Comparable<Node> {
		int r, c, v;

		public Node(int r, int c, int v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}

		@Override
		public int compareTo(Node o) {
			return this.v - o.v;
		}
	}
}
