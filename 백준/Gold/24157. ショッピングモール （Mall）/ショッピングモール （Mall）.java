import java.io.*;
import java.util.*;

public class Main {
	static int N, M, A, B;
	static int[][] map, P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		P = new int[N + 1][M + 1];
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				int v = Integer.parseInt(st.nextToken());
				P[n][m] = P[n - 1][m] + P[n][m - 1] - P[n - 1][m - 1] + (v == -1 ? 1 : 0);
				if (v == -1) {
					map[n][m] = 0;
					continue;
				}
				map[n][m] = v + map[n - 1][m] + map[n][m - 1] - map[n - 1][m - 1];
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int n = 0; n <= N - A; n++) {
			for (int m = 0; m <= M - B; m++) {
				if ((P[n + A][m + B] + P[n][m] - P[n + A][m] - P[n][m + B]) != 0)
					continue;
				answer = Math.min(answer, map[n][m] + map[n + A][m + B] - map[n][m + B] - map[n + A][m]);
			}
		}
		System.out.println(answer);
	}
}