import java.io.*;
import java.util.*;

public class Main {
	static int N, M, P, T;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Pos>[] queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		queue = new ArrayDeque[P + 1];
		for (int p = 1; p <= P; p++)
			queue[p] = new ArrayDeque<>();
		int[] S = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int n = 0; n < N; n++) {
			map[n] = br.readLine().toCharArray();
			for (int m = 0; m < M; m++) {
				if (map[n][m] == '.')
					continue;
				if (map[n][m] == '#')
					T++;
				else {
					T++;
					visited[n][m] = true;
					queue[map[n][m] - '0'].add(new Pos(n, m));
				}
			}
		}
		outer: while (true) {
			int pn = 0;
			for (int p = 0; p < P; p++) {
				for (int s = 0; s < S[p]; s++) {
					solution(p + 1);
					if (queue[p + 1].isEmpty())
						break;
					if (T == N * M)
						break outer;
				}
				pn += queue[p + 1].size();
			}
			if (pn == 0)
				break;
		}
		int A[] = new int[P];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == '#' || map[r][c] == '.')
					continue;
				A[map[r][c] - '1']++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int p = 0; p < P; p++)
			sb.append(A[p]).append(" ");
		System.out.println(sb);
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void solution(int n) {
		for (int i = 0, size = queue[n].size(); i < size; i++) {
			Pos p = queue[n].poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] != '.')
					continue;
				T++;
				visited[nr][nc] = true;
				map[nr][nc] = map[p.r][p.c];
				queue[n].offer(new Pos(nr, nc));
			}

		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
