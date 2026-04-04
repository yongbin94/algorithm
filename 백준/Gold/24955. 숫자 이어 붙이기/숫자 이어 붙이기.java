import java.io.*;
import java.util.*;

public class Main {
	static int N, Q;
	static long[] A;
	static List<Integer>[] edges;
	static List<Integer> path;
	static boolean[] visited;
	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		A = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}

		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}

		StringBuilder sb = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			path = new ArrayList<>();
			visited = new boolean[N + 1];
			dfs(x, y);

			long res = 0;
			for (int n : path) {
				long v = A[n];
				long p = 10;
				long tmp = v;
				while (tmp >= 10) {
					tmp /= 10;
					p = (p * 10) % MOD;
				}

				res = (res * p) % MOD;
				res = (res + v) % MOD;
			}
			sb.append(res).append("\n");
		}
		System.out.print(sb);
	}

	private static boolean dfs(int x, int y) {
		visited[x] = true;
		path.add(x);

		if (x == y) return true;

		for (int next : edges[x]) {
			if (visited[next]) continue;
			if (dfs(next, y)) return true;
		}

		path.remove(path.size() - 1);
		return false;
	}
}