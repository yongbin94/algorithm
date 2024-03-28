import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] map;
	static Queue<Pos> gsdc, water;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		gsdc = new ArrayDeque<>();
		water = new ArrayDeque<>();
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++)
				if (map[r][c] == 'S')
					gsdc.offer(new Pos(r, c));
				else if (map[r][c] == '*')
					water.offer(new Pos(r, c));
		}
		int time = 0;
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		while (!gsdc.isEmpty()) {
			time++;
			for (int i = 0, size = water.size(); i < size; i++) {
				Pos p = water.poll();
				for(int d = 0; d< 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if(!isIn(nr,nc) || map[nr][nc] == '*' || map[nr][nc] == 'X' || map[nr][nc] == 'D')
						continue;
					map[nr][nc] = '*';
					water.offer(new Pos(nr, nc));
				}
			}
			for (int i = 0, size = gsdc.size(); i < size; i++) {
				Pos p = gsdc.poll();
					
				for(int d = 0; d< 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if(!isIn(nr,nc) || map[nr][nc] == '*' || map[nr][nc] == 'X' || map[nr][nc] == 'S')
						continue;
					if(map[nr][nc] == 'D') {
						System.out.println(time);
						return;
					}
					map[nr][nc] = 'S';
					gsdc.offer(new Pos(nr, nc));
				}
			}
		}
		System.out.println("KAKTUS");
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	public static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
