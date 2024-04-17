import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static List<Info> list;
	static int[][] map, board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		list = new ArrayList<>();
		list.add(null);
		for (int k = 1; k <= K; k++) {
			st = new StringTokenizer(br.readLine());
			int no = k;
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			board[r][c] = k;
			list.add(new Info(no, r, c, d));
		}
		int T = 0;
		outer: while (++T <= 1000)
			for (Info info : list)
				if (info != null && move(info))
					break outer;
		System.out.println(T > 1000 ? -1 : T);
	}

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int[] mask = { 0, 15, 240, 3840 };

	private static boolean move(Info info) {
		int nr = info.r + dr[info.d];
		int nc = info.c + dc[info.d];
		if (!isIn(nr, nc) || map[nr][nc] == 2) {
			info.d = info.d % 2 == 0 ? info.d + 1 : info.d - 1;
			nr = info.r + dr[info.d];
			nc = info.c + dc[info.d];
			if (!isIn(nr, nc) || map[nr][nc] == 2)
				return false;
		}
		int num = 0, idx = 0;
		for (int i = 0; i < 3; i++) {
			int tmp = board[info.r][info.c] & mask[++idx];
			num += tmp;
			if ((tmp >> (4 * (idx - 1))) == info.no)
				break;
		}

		int cnt = 0;
		for (int i = 1; i <= 3; i++) {
			if ((board[nr][nc] & mask[i]) != 0)
				cnt++;
			if ((num & mask[i]) != 0)
				cnt++;
		}
		if (cnt >= 4)
			return true;

		board[nr][nc] <<= (idx * 4);
		board[info.r][info.c] >>= (idx * 4);

		for (int i = 1; i <= 3; i++) {
			int tmp = (num & mask[i]) >> (i - 1) * 4;
			if (tmp == 0)
				break;
			list.get(tmp).r = nr;
			list.get(tmp).c = nc;
		}

		if (map[nr][nc] == 1) {
			if (idx == 2)
				num = (num >> 4) | ((num & mask[1]) << 4);
			if (idx == 3)
				num = (num & ~mask[1] & ~mask[3]) | ((num & mask[1]) << 8) | (num >> 8);
		}
		board[nr][nc] += num;
		return false;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static class Info {
		int no, r, c, d;

		public Info(int no, int r, int c, int d) {
			this.no = no;
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}
}
