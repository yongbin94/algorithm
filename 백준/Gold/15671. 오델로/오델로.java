import java.io.*;
import java.util.*;

public class Main {
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		map = new char[6][6];
		for (int i = 0; i < 6; i++)
			Arrays.fill(map[i], '.');
		map[2][2] = 'W';
		map[3][3] = 'W';
		map[2][3] = 'B';
		map[3][2] = 'B';
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			char p = t % 2 == 0 ? 'B' : 'W';
			map[r][c] = p;
			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				play(nr, nc, d, p);
			}
		}
		StringBuilder sb = new StringBuilder();
		int b = 0, w = 0;
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 6; c++) {
				char ch = map[r][c];
				sb.append(ch);
				if (ch == 'B')
					b++;
				if (ch == 'W')
					w++;
			}
			sb.append("\n");
		}
		sb.append(b > w ? "Black" : "White");
		System.out.println(sb);
	}

	static int[] dr = { 0, 1, 0, -1, 1, -1, 1, -1 };
	static int[] dc = { 1, 0, -1, 0, 1, 1, -1, -1 };

	private static boolean play(int r, int c, int d, char p) {
		if (!isIn(r, c) || map[r][c] == '.')
			return false;
		if (map[r][c] == p)
			return true;
		if (play(r + dr[d], c + dc[d], d, p)) {
			map[r][c] = p;
			return true;
		}
		return false;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < 6 && c >= 0 && c < 6;
	}
}