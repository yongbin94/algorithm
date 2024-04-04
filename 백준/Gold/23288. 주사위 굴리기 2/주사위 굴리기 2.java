import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, R, C, D, result;
	static int[] dice = { 756, 882 };
	static int[][] map, score;
	static boolean[][] vistied;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][];
		score = new int[N][M];
		for (int n = 0; n < N; n++)
			map[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		solution();
		System.out.println(result);
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void solution() {
		setScoreMap();
		while (K-- > 0) {
			move();
			roll();
			setDirection();
		}
	}

	private static void setScoreMap() {
		vistied = new boolean[N][M];
		for (int i = 0; i < N * M; i++)
			if (!vistied[i / M][i % M])
				getScore(i / M, i % M);
	}

	private static void getScore(int r, int c) {
		Queue<Pos> q = new ArrayDeque<>();
		Queue<Pos> tmp = new ArrayDeque<>();
		q.offer(new Pos(r, c));
		vistied[r][c] = true;
		int n = map[r][c];
		int cnt = 0;
		while (!q.isEmpty()) {
			Pos p = q.poll();
			tmp.offer(p);
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (!isIn(nr, nc) || vistied[nr][nc] || map[nr][nc] != n)
					continue;
				vistied[nr][nc] = true;
				q.offer(new Pos(nr, nc));
			}
			cnt++;
		}
		int v = n * cnt;
		for (Pos p : tmp)
			score[p.r][p.c] = v;
	}

	private static void move() {
		int nr = R + dr[D];
		int nc = C + dc[D];
		if (!isIn(nr, nc)) {
			D = (D + 2) % 4;
			nr = R + dr[D];
			nc = C + dc[D];
		}
		R = nr;
		C = nc;
		result += score[R][C];
	}

	private static void roll() {
		int i = D % 2, j = (D + 1) % 2;
		dice[i] = D / 2 == 0 ? (dice[i] & 7) << 9 | dice[i] >> 3 // 우 하
				: (dice[i] & 511) << 3 | dice[i] >> 9; // 좌 상
		dice[j] = (dice[j] & 455) | (dice[i] & 3640);
	}

	private static void setDirection() {
		int bottom = (dice[0] >> 3) & 7;
		D = bottom == map[R][C] ? D : bottom > map[R][C] ? (D + 1) % 4 : (D - 1 + 4) % 4;
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
