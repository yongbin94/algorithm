import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int n = 0; n < N; n++) {
			Arrays.fill(map[n], INF);
			map[n][n] = 0;
		}
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			map[s][e] = w;
			map[e][s] = w;
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		int min = INF, answer = -1;
		for (int n = 0; n < N; n++) {
			int sum = Arrays.stream(map[n]).sum();
			if (min > sum) {
				min = sum;
				answer = n + 1;
			}
		}
		System.out.println(answer);
	}
}