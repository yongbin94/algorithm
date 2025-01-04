import java.io.*;
import java.util.*;

public class Main {
	static Pos S, E;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			S = new Pos(st.nextToken().charAt(0) - 'A', Integer.parseInt(st.nextToken()) - 1);
			E = new Pos(st.nextToken().charAt(0) - 'A', Integer.parseInt(st.nextToken()) - 1);
			solution();
		}
		System.out.println(sb);
	}

	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, 1, -1 };

	private static void solution() {
		if ((S.r + S.c) % 2 != (E.r + E.c) % 2) {
			sb.append("Impossible\n");
			return;
		}
		if (S.r == E.r && S.c == E.c) {
			print(0, null);
			return;
		}
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(S);
		int time = 1;
		while (!q.isEmpty()) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Pos p = q.poll();
				for (int d = 0; d < 4; d++) {
					for (int n = -7; n < 8; n++) {
						int nr = p.r + dr[d] * n;
						int nc = p.c + dc[d] * n;
						if (!isIn(nr, nc))
							continue;
						if (E.r == nr && E.c == nc) {
							print(time, p);
							return;
						}
						if (time == 1)
							q.offer(new Pos(nr, nc));
					}
				}
			}
			time++;
		}
	}

	private static void print(int time, Pos p) {
		sb.append(time).append(" ");
		if (time > 0)
			sb.append((char) ('A' + S.r)).append(" ").append(S.c + 1).append(" ");
		if (time > 1)
			sb.append((char) ('A' + p.r)).append(" ").append(p.c + 1).append(" ");
		sb.append((char) ('A' + E.r)).append(" ").append(E.c + 1).append("\n");
		return;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < 8 && c >= 0 && c < 8;
	}

	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}