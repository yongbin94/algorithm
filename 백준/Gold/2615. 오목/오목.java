import java.io.*;
import java.util.*;

public class Main {
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = new int[21][21];
		for (int n = 1; n < 20; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int m = 1; m < 20; m++) {
				A[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		for (int m = 1; m < 20; m++) {
			for (int n = 1; n < 20; n++) {
				if (A[n][m] == 0) continue;
				if (solution(n, m)) {
					System.out.printf("%d\n%d %d", A[n][m], n, m);
					return;
				}
			}
		}
        System.out.println(0);
	}

	static int[] dr = { -1, 0, 1, 1 };
	static int[] dc = { 1, 1, 1, 0 };

	private static boolean solution(int n, int m) {
		d: for (int d = 0; d < 4; d++) {
			if (A[n - dr[d]][m - dc[d]] == A[n][m]) continue;
			int nr = n + dr[d];
			int nc = m + dc[d];
			for (int i = 1; i < 5; i++) {
				if (!isIn(nr, nc) || A[nr][nc] != A[n][m]) continue d;
				nr += dr[d];
				nc += dc[d];
			}
			return A[nr][nc] != A[n][m];
		}
		return false;
	}

	private static boolean isIn(int r, int c) {
		return r > 0 && r < 20 && c > 0 && c < 20;
	}
}