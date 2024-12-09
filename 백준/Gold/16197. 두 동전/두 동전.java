import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static Pos A;
	static boolean[][][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		int tr = -1, tc = -1;
		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == 'o') {
					if (tr == -1) {
						tr = r;
						tc = c;
					} else {
						A = new Pos(tr, tc, r, c);
						visited[tr][tc][r][c] = true;
					}
				}
			}
		}
		solution();
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void solution() {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(A);
		int time = 1;
		while (!q.isEmpty()) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				for (int d = 0; d < 4; d++) {
					int nar = p.ar + dr[d];
					int nac = p.ac + dc[d];
					int nbr = p.br + dr[d];
					int nbc = p.bc + dc[d];
					boolean a = false;
					boolean b = false;
					if (!isIn(nar, nac))
						a = true;
					if (!isIn(nbr, nbc))
						b = true;
					if (a && b)
						continue;
					if (a || b) {
						System.out.println(time);
						return;
					}
					if (!a && (map[nar][nac] == '#')) {
						nar = p.ar;
						nac = p.ac;
					}
					if (!b && (map[nbr][nbc] == '#')) {
						nbr = p.br;
						nbc = p.bc;
					}
					if (visited[nar][nac][nbr][nbc])
						continue;
					visited[nar][nac][nbr][nbc] = true;
					q.offer(new Pos(nar, nac, nbr, nbc));
				}
			}
			if (++time > 10)
				break;
		}
		System.out.println(-1);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static class Pos {
		int ar, ac, br, bc;

		public Pos(int ar, int ac, int br, int bc) {
			this.ar = ar;
			this.ac = ac;
			this.br = br;
			this.bc = bc;
		}
	}
}