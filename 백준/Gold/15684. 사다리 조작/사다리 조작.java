import java.io.*;
import java.util.*;

public class Main {
	static int N, M, H;
	static int[][] map;
	static List<Pos> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][N];
		list = new ArrayList<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;
			map[a][b + 1] = 2;
		}
		for (int r = 0; r < H; r++)
			for (int c = 0; c < N - 1; c++)
				if (map[r][c] == 0 && map[r][c + 1] == 0)
					list.add(new Pos(r, c));

		for (int i = 0; i <= 3; i++) {
			if (recur(0, 0, i)) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}

	private static boolean recur(int cnt, int start, int a) {
		if (cnt == a) {
			boolean flag = true;
			for (int i = 0; i < N; i++) {
				if (!check(i)) {
					flag = false;
					break;
				}
			}
			return flag;
		}
		for (int i = start; i < list.size(); i++) {
			Pos p = list.get(i);
			if (map[p.r][p.c] != 0)
				continue;
			map[p.r][p.c] = 1;
			map[p.r][p.c + 1] = 2;
			if (recur(cnt + 1, i + 1, a))
				return true;
			map[p.r][p.c] = 0;
			map[p.r][p.c + 1] = 0;
		}
		return false;
	}

	private static boolean check(int s) {
		int r = 0, c = s;
		while (r < H) {
			int v = map[r][c];
			if (v == 1)
				c++;
			else if (v == 2)
				c--;
			r++;
		}
		return s == c;
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
