import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static int[] dn = { -1, 0, 1, 0 };
	static int[] dm = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 2][M + 2];

		Arrays.fill(map[0], 1);
		Arrays.fill(map[N + 1], 1);
		for (int n = 1; n <= N; n++) {
			map[n][0] = 1;
			map[n][M + 1] = 1; // 여기 M -> M + 1
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		for (int a = 0; a < N * M; a++) {
			int n1 = a / M + 1;
			int m1 = a % M + 1;
			if (map[n1][m1] != 0) continue;
			map[n1][m1] = 1;
			for (int b = a + 1; b < N * M; b++) {
				int n2 = b / M + 1;
				int m2 = b % M + 1;
				if (map[n2][m2] != 0) continue;
				map[n2][m2] = 1;
				answer = Math.max(answer, solution());
				map[n2][m2] = 0;
			}
			map[n1][m1] = 0;
		}
		System.out.println(answer);
	}

	private static int solution() {
		int res = 0;
		visited = new boolean[N + 2][M + 2];
		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				if (map[n][m] != 2 || visited[n][m]) continue;
				res += bfs(n, m);
			}
		}
		return res;
	}

	private static int bfs(int n, int m) {
		int res = 0;
		boolean flag = false;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(n, m));
		visited[n][m] = true;

		while (!q.isEmpty()) {
			Pos p = q.poll();
			res++;
			for (int d = 0; d < 4; d++) {
				int nn = p.n + dn[d];
				int nm = p.m + dm[d];
				if (map[nn][nm] == 0) {
					flag = true;
					continue;
				}
				if (map[nn][nm] == 1 || visited[nn][nm]) continue;
				visited[nn][nm] = true;
				q.offer(new Pos(nn, nm));
			}
		}
		return flag ? 0 : res;
	}

	private static class Pos {
		int n, m;

		Pos(int n, int m) {
			this.n = n;
			this.m = m;
		}
	}
}