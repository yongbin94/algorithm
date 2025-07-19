import java.io.*;
import java.util.*;

public class Main {
	static int A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			A <<= 4;
			A |= Integer.parseInt(br.readLine(), 2);
		}
		for (int i = 0; i < 4; i++) {
			B <<= 4;
			B |= Integer.parseInt(br.readLine(), 2);
		}
		System.out.println(solution());
		
	}

	static int[] dr = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dc = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] di = { 6, 9, 7, 2, -6, -9, -7, -2 };

	private static int solution() {
		Set<Integer> set = new HashSet<>();
		Queue<Integer> q = new ArrayDeque<>();
		set.add(A);
		q.offer(A);
		int time = 0;
		while (!q.isEmpty()) {
			for (int x = 0, size = q.size(); x < size; x++) {
				int v = q.poll();
				if (v == B) return time;
				for (int n = 0; n < 16; n++) {
					int i = 15 - n;
					if (((v >> i) & 1) == 0) continue;
					int r = n / 4;
					int c = n % 4;
					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						int ni = i + di[d];
						if (!isIn(nr, nc) || ((v >> ni) & 1) == 1) continue;
						int nv = v ^ (1 << i) | (1 << ni);
						if (set.contains(nv)) continue;
						set.add(nv);
						q.offer(nv);
					}
				}

			}
			time++;
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4;
	}
}