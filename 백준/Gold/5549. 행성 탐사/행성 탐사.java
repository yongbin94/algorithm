import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static int[][] J, O, I;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		J = new int[N + 1][M + 1];
		O = new int[N + 1][M + 1];
		I = new int[N + 1][M + 1];
		int T = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++)
			map[n] = br.readLine().toCharArray();
		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				char c = map[n - 1][m - 1];
				J[n][m] = J[n][m - 1] + J[n - 1][m] - J[n - 1][m - 1] + (c == 'J' ? 1 : 0);
				O[n][m] = O[n][m - 1] + O[n - 1][m] - O[n - 1][m - 1] + (c == 'O' ? 1 : 0);
				I[n][m] = I[n][m - 1] + I[n - 1][m] - I[n - 1][m - 1] + (c == 'I' ? 1 : 0);
			}
		}
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int an = Integer.parseInt(st.nextToken()) - 1;
			int am = Integer.parseInt(st.nextToken()) - 1;
			int bn = Integer.parseInt(st.nextToken());
			int bm = Integer.parseInt(st.nextToken());
			sb.append(J[bn][bm] - J[an][bm] - J[bn][am] + J[an][am]).append(" ");
			sb.append(O[bn][bm] - O[an][bm] - O[bn][am] + O[an][am]).append(" ");
			sb.append(I[bn][bm] - I[an][bm] - I[bn][am] + I[an][am]).append("\n");
		}
		System.out.println(sb);
	}
}