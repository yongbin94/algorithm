import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int n = 0; n < N; n++) {
			map[n] = br.readLine().toCharArray();
		}
		st = new StringTokenizer(br.readLine());
		int PR = Integer.parseInt(st.nextToken()) - 1;
		int PC = Integer.parseInt(st.nextToken()) - 1;
		int max = -1, D = -1;
		for (int d = 0; d < 4; d++) {
			int cnt = dfs(PR, PC, d);
			if(max < cnt) {
				max = cnt;
				D = d;
			}
		}
		System.out.println("URDL".charAt(D));
		System.out.println(max == Integer.MAX_VALUE ? "Voyager" : max);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static int dfs(int r, int c, int d) {
		int cnt = 0;
		boolean[][][] visited = new boolean[N][M][4];
		while (true) {
			if (!isIn(r, c) || map[r][c] == 'C') return cnt;
			if (visited[r][c][d]) return Integer.MAX_VALUE;
			visited[r][c][d] = true;
			if (map[r][c] == '\\') d = (d + (d % 2 == 0 ? 3 : 1)) % 4;
			if (map[r][c] == '/') d = (d + (d % 2 == 0 ? 1 : 3)) % 4;
			r += dr[d];
			c += dc[d];
			cnt++;
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}