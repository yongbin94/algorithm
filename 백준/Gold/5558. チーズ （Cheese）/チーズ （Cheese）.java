import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int r = 0, c = 0;
		map = new char[N][];
		visited = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			map[n] = str.toCharArray();
			if (str.indexOf('S') != -1) {
				r = n;
				c = str.indexOf('S');
			}
		}
		bfs(r, c);
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };

	private static void bfs(int r, int c) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(r, c));
		visited[r][c] = true;
		int count = 0;
		int k = 1;
		outer: while (k <= K) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				if (map[p.r][p.c] == k + '0') {
					k++;
					q.clear();
					q.add(new Pos(p.r, p.c));
					visited = new boolean[N][M];
					visited[p.r][p.c] = true;
					continue outer;
				}

				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == 'X')
						continue;
					visited[nr][nc] = true;
					q.add(new Pos(nr, nc));
				}
			}
			count++;

		}
		System.out.println(count);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
