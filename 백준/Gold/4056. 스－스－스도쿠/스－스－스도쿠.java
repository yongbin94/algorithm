import java.io.*;
import java.util.*;

public class Main {
	static int[][] board, answer;
	static int[] A, B, C;
	static List<Pos> P;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			board = new int[9][9];
			answer = new int[9][9];
			A = new int[9];
			B = new int[9];
			C = new int[9];
			P = new ArrayList<>();
			cnt = 0;
			boolean valid = true;
			for (int r = 0; r < 9; r++) {
				String input = br.readLine();
				for (int c = 0; c < 9; c++) {
					int v = input.charAt(c) - '0';
					board[r][c] = v;
					if (v == 0) {
						P.add(new Pos(r, c));
						continue;
					}
					int box = r / 3 * 3 + c / 3;
					if (((A[r] >> v) & 1) == 1 || ((B[c] >> v) & 1) == 1 || ((C[box] >> v) & 1) == 1) {
						valid = false;
					}

					A[r] |= 1 << v;
					B[c] |= 1 << v;
					C[box] |= 1 << v;
				}
			}

			if (valid) recur(0);
			if (valid && cnt == 1) {
				for (int r = 0; r < 9; r++) {
					for (int c = 0; c < 9; c++) {
						sb.append(answer[r][c]);
					}
					sb.append("\n");
				}
			} else {
				sb.append("Could not complete this grid.\n");
			}
			if (T > 0)
				sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void recur(int n) {
		if (n == P.size()) {
			if (cnt++ > 0) return;
			if (cnt == 1) {
				for (int r = 0; r < 9; r++) {
					answer[r] = Arrays.copyOf(board[r], 9);
				}
			}
			return;
		}
		Pos p = P.get(n);
		int box = p.r / 3 * 3 + p.c / 3;
		int bit = A[p.r] | B[p.c] | C[box];
		for (int i = 1; i <= 9; i++) {
			if ((bit & (1 << i)) != 0) continue;
			A[p.r] |= 1 << i;
			B[p.c] |= 1 << i;
			C[box] |= 1 << i;
			board[p.r][p.c] = i;
			recur(n + 1);
			A[p.r] &= ~(1 << i);
			B[p.c] &= ~(1 << i);
			C[box] &= ~(1 << i);
			board[p.r][p.c] = 0;
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