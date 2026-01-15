import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static boolean[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N + 1][M + 1];

		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int cnt) {
		if (cnt == N * M) {
			answer++;
			return;
		}

		int r = cnt / M + 1;
		int c = cnt % M + 1;

		dfs(cnt + 1);

		if (r > 1 && c > 1 && map[r - 1][c] && map[r][c - 1] && map[r - 1][c - 1]) return;

		map[r][c] = true;
		dfs(cnt + 1);
		map[r][c] = false;
	}
}
