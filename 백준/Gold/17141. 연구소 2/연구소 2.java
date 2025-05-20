import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static int[][] map;
	static List<Pos> list;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		list = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int v = Integer.parseInt(st.nextToken());
				map[r][c] = v;
				if (v == 2) list.add(new Pos(r, c));
			}
		}
		selected = new boolean[list.size()];
		answer = Integer.MAX_VALUE;
		recur(0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void recur(int cnt, int start) {
		if (cnt == M) {
			answer = Math.min(answer, bfs());
			return;
		}
		for (int i = start; i < list.size(); i++) {
			selected[i] = true;
			recur(cnt + 1, i + 1);
			selected[i] = false;
		}
	}

	static int tmp = 3;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static int bfs() {
		tmp++;
		Queue<Pos> q = new ArrayDeque<>();
		for (int i = 0; i < list.size(); i++) {
			if (!selected[i]) continue;
			Pos p = list.get(i);
			map[p.r][p.c] = tmp;
			q.offer(new Pos(p.r, p.c));
		}
		int time = -1;
		while(!q.isEmpty()) {
			for(int i = 0, size = q.size(); i < size;i++ ) {
				Pos p = q.poll();
				for(int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if(!isIn(nr, nc) || map[nr][nc] == 1 || map[nr][nc] == tmp) continue;
					map[nr][nc] = tmp;
					q.offer(new Pos(nr, nc));
				}
			}
			time++;
		}
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] == tmp || map[r][c] == 1) continue; 
				return Integer.MAX_VALUE;
			}
		}
		return time;
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