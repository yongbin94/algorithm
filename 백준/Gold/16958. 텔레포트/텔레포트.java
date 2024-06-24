import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		Pos[] A = new Pos[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			A[n] = new Pos(s, x, y);
		}
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (i == j)
					continue;
				int d = Math.min(Math.abs(A[i].x - A[j].x) + Math.abs(A[i].y - A[j].y),
						(A[i].s == 1 && A[j].s == 1) ? T : Integer.MAX_VALUE);
				map[i][j] = d;
				map[j][i] = d;
			}
		}
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					int d = Math.min(map[i][j], map[i][k] + map[k][j]);

					map[i][j] = d;
					map[j][i] = d;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			sb.append(map[s][e]).append("\n");
		}
		System.out.println(sb);
	}

	private static class Pos {
		int s, x, y;

		public Pos(int s, int x, int y) {
			this.s = s;
			this.x = x;
			this.y = y;
		}
	}
}
