import java.io.*;
import java.util.*;

public class Main {
	static int[][] A;
	static boolean[] selected;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			A = new int[11][11];
			selected = new boolean[11];
			res = 0;
			for (int i = 0; i < 11; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 11; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			recur(0, 0);
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	private static void recur(int i, int score) {
		if (i == 11) {
			res = Math.max(res, score);
			return;
		}
		for (int j = 0; j < 11; j++) {
			if (selected[j] || A[i][j] == 0) continue;
			selected[j] = true;
			recur(i + 1, score + A[i][j]);
			selected[j] = false;
		}
	}
}