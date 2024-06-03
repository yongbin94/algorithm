import java.io.*;
import java.util.*;
public class Main {
	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][];
		visited = new boolean[2][N][M];
		for (int n = 0; n < N; n++)
			map[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		solution();
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void solution() {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(0, 0, false));
		visited[0][0][0] = true;
		int time = 0;
		while (time <= T) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				if (p.r == N - 1 && p.c == M - 1) {
					System.out.println(time);
					return;
				}
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					boolean ng = p.g;
					if (!isIn(nr, nc) || visited[ng ? 1 : 0][nr][nc] || (!p.g && map[nr][nc] == 1))
						continue;
					if (map[nr][nc] == 2)
						ng = true;
					visited[ng ? 1 : 0][nr][nc] = true;
					q.offer(new Pos(nr, nc, ng));
				}
			}
			time++;
		}
		System.out.println("Fail");
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static class Pos {
		int r, c;
		boolean g;

		public Pos(int r, int c, boolean g) {
			this.r = r;
			this.c = c;
			this.g = g;
		}

	}
}