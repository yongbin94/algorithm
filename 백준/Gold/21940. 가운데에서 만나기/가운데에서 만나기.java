import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] dist = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			dist[u][v] = Math.min(dist[u][v], t);
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		int K = Integer.parseInt(br.readLine());
		int[] A = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int[] max = new int[N + 1];
		int res = Integer.MAX_VALUE;

		for (int x = 1; x <= N; x++) {
			int curr = 0;
			boolean flag = true;

			for (int a : A) {
				if (dist[a][x] == INF || dist[x][a] == INF) {
					flag = false;
					break;
				}
				curr = Math.max(curr, dist[a][x] + dist[x][a]);
			}

			if (flag) {
				max[x] = curr;
				res = Math.min(res, curr);
			} else {
				max[x] = INF;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (max[i] == res) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}
}