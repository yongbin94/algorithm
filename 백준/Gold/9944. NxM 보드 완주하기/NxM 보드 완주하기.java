import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, min;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = "";
		int T = 1;
		while ((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new boolean[N][M];
			K = N * M;
			min = Integer.MAX_VALUE;
			for (int r = 0; r < N; r++) {
				String str = br.readLine();
				for (int c = 0; c < M; c++) {
					if (str.charAt(c) == '*') {
						map[r][c] = true;
						K--;
					}
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (!map[r][c]) {
						map[r][c] = true;
						recur(r, c, 1, 0);
						map[r][c] = false;
					}
				}
			}
			sb.append("Case ").append(T++).append(": ").append(min == Integer.MAX_VALUE ? -1 : min).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void recur(int r, int c, int k, int cnt) {
		if (min <= cnt) return;
		if (k == K) {
			min = Math.min(min, cnt);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			int x = 0;
			while (isIn(nr, nc) && !map[nr][nc]) {
				x++;
				map[nr][nc] = true;
				nr += dr[d];
				nc += dc[d];
			}
			if (x > 0) {
				nr -= dr[d];
				nc -= dc[d];
				recur(nr, nc, k + x, cnt + 1);
			}
			for (int i = 1; i <= x; i++) {
				map[r + (dr[d] * i)][c + (dc[d] * i)] = false;
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}