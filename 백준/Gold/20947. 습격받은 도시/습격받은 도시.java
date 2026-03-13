import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		boolean[][] A = new boolean[N][N];

		for (int n = 0; n < N; n++) {
			map[n] = br.readLine().toCharArray();
		}

		for (int r = 0; r < N; r++) {
			boolean flag = false;
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 'O') flag = true;
				else if (map[r][c] == 'X') flag = false;
				else if (flag) A[r][c] = true;
			}
			flag = false;
			for (int c = N - 1; c >= 0; c--) {
				if (map[r][c] == 'O') flag = true;
				else if (map[r][c] == 'X') flag = false;
				else if (flag) A[r][c] = true;
			}
		}

		for (int c = 0; c < N; c++) {
			boolean flag = false;
			for (int r = 0; r < N; r++) {
				if (map[r][c] == 'O') flag = true;
				else if (map[r][c] == 'X') flag = false;
				else if (flag) A[r][c] = true;
			}
			flag = false;
			for (int r = N - 1; r >= 0; r--) {
				if (map[r][c] == 'O') flag = true;
				else if (map[r][c] == 'X') flag = false;
				else if (flag) A[r][c] = true;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == '.' && !A[r][c]) map[r][c] = 'B';
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}