import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static List<MaskingTape> list;
	static PriorityQueue<Integer> rPq, cPq;
	static Map<Integer, Integer> rMap, cMap;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		rPq = new PriorityQueue<>();
		cPq = new PriorityQueue<>();
		rMap = new HashMap<>();
		cMap = new HashMap<>();
		list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			list.add(new MaskingTape(new StringTokenizer(br.readLine())));
		}
		compress();
		imos();
		System.out.println(bfs());
	}

	private static void compress() {
		int v = 0;
		int i = rPq.peek() == 0 ? 0 : 1;
		while (!rPq.isEmpty()) {
			v = rPq.poll();
			if (rMap.containsKey(v)) continue;
			rMap.put(v, i++);
		}
		R = i + (v == R ? -1 : 0);

		i = cPq.peek() == 0 ? 0 : 1;
		while (!cPq.isEmpty()) {
			v = cPq.poll();
			if (cMap.containsKey(v)) continue;
			cMap.put(v, i++);
		}
		C = i + (v == C ? -1 : 0);
	}

	private static void imos() {
		A = new int[R + 1][C + 1];
		for (MaskingTape mt : list) {
			mt.tape();
		}

		for (int r = 1; r <= R; r++) {
			A[r][0] += A[r - 1][0];
		}
		
		for (int c = 1; c <= C; c++) {
			A[0][c] += A[0][c - 1];
		}

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				A[r][c] += A[r - 1][c] + A[r][c - 1] - A[r - 1][c - 1];
			}
		}
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static int bfs() {
		boolean[][] visited = new boolean[R][C];
		Queue<Pos> q = new ArrayDeque<>();
		int res = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (A[r][c] != 0 || visited[r][c]) continue;
				res++;
				visited[r][c] = true;
				q.offer(new Pos(r, c));
				while (!q.isEmpty()) {
					Pos p = q.poll();
					for (int d = 0; d < 4; d++) {
						int nr = p.r + dr[d];
						int nc = p.c + dc[d];
						if (!isIn(nr, nc) || visited[nr][nc] || A[nr][nc] != 0) continue;
						visited[nr][nc] = true;
						q.offer(new Pos(nr, nc));
					}
				}
			}
		}
		return res;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	private static class MaskingTape {
		int sr, sc, er, ec;

		public MaskingTape(StringTokenizer st) {
			sc = Integer.parseInt(st.nextToken());
			sr = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());
			er = Integer.parseInt(st.nextToken());
			cPq.offer(sc);
			rPq.offer(sr);
			cPq.offer(ec);
			rPq.offer(er);
		}

		public void tape() {
			A[rMap.get(sr)][cMap.get(sc)]++;
			A[rMap.get(er)][cMap.get(sc)]--;
			A[rMap.get(sr)][cMap.get(ec)]--;
			A[rMap.get(er)][cMap.get(ec)]++;
		}
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}