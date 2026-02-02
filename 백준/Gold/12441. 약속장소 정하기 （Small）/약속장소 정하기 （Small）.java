import java.io.*;
import java.util.*;

public class Main {
	static final long INF = Long.MAX_VALUE / 4;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] X = new int[P];
			int[] V = new int[P];
			for (int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				X[i] = Integer.parseInt(st.nextToken());
				V[i] = Integer.parseInt(st.nextToken());
			}

			long[][] dist = new long[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				Arrays.fill(dist[i], INF);
				dist[i][i] = 0;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int D = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());

				int prev = Integer.parseInt(st.nextToken());
				for (int j = 1; j < L; j++) {
					int curr = Integer.parseInt(st.nextToken());
					dist[prev][curr] = dist[curr][prev] = Math.min(dist[prev][curr], D);
					prev = curr;
				}
			}

			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (dist[i][k] + dist[k][j] < dist[i][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					}
				}
			}

			long min = INF;
			boolean possible = false;

			for (int target = 1; target <= N; target++) {
				long max = 0;
				boolean flag = true;

				for (int i = 0; i < P; i++) {
					if (dist[X[i]][target] == INF) {
						flag = false;
						break;
					}
					max = Math.max(max, dist[X[i]][target] * V[i]);
				}

				if (flag) {
					possible = true;
					min = Math.min(min, max);
				}
			}

			sb.append("Case #").append(t).append(": ").append(possible ? min : -1).append("\n");
		}
		System.out.println(sb);
	}
}