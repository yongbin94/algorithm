import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		for (int n = 0; n < R; n++)
			map[n] = br.readLine().toCharArray();

		int result = 0;
		for (int i = 0; i < R; i++)
			if (dfs(i, 0))
				result++;
		System.out.println(result);
	}

	static int[] dr = { -1, 0, 1 };

	private static boolean dfs(int r, int c) {
		if (c == C - 1)
			return true;
		for (int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + 1;
			if (!isIn(nr, nc) || map[nr][nc] != '.')
				continue;
			map[nr][nc] = '*';
			if (dfs(nr, nc))
				return true;
		}
		return false;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c < C;
	}
}
