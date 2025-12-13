import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] map;
	static List<Pos> T, X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		T = new ArrayList<>();
		X = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = st.nextToken().charAt(0);
				if (map[r][c] == 'T') T.add(new Pos(r, c));
				else if (map[r][c] == 'X') X.add(new Pos(r, c));
			}
		}
		for (int i = 0; i < X.size(); i++) {
			map[X.get(i).r][X.get(i).c] = 'O';
			for (int j = i + 1; j < X.size(); j++) {
				map[X.get(j).r][X.get(j).c] = 'O';
				for (int k = j + 1; k < X.size(); k++) {
					map[X.get(k).r][X.get(k).c] = 'O';
					if (solution()) {
						System.out.println("YES");
						return;
					}
					map[X.get(k).r][X.get(k).c] = 'X';
				}
				map[X.get(j).r][X.get(j).c] = 'X';
			}
			map[X.get(i).r][X.get(i).c] = 'X';
		}
		System.out.println("NO");
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static boolean solution() {
		for (Pos t : T) {
			for (int d = 0; d < 4; d++) {
				int r = t.r + dr[d];
				int c = t.c + dc[d];
				while (isIn(r, c)) {
					if (map[r][c] == 'S') return false;
					if (map[r][c] == 'O') break;
					r += dr[d];
					c += dc[d];
				}
			}
		}
		return true;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}