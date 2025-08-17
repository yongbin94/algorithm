import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][][] S = new int[N + 1][N + 1][11];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int v = Integer.parseInt(st.nextToken());
				for (int k = 1; k <= 10; k++) {
					S[r][c][k] = S[r - 1][c][k] + S[r][c - 1][k] - S[r - 1][c - 1][k] + (v == k ? 1 : 0);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int cnt = 0;
			for (int k = 1; k <= 10; k++) {
				if (S[x2][y2][k] - S[x1][y2][k] - S[x2][y1][k] + S[x1][y1][k] > 0) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}