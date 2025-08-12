import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] A;
	static List<Integer>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edges[u].add(v);
			edges[v].add(u);
		}

		System.out.println(dfs(K, 0, A[K]) ? 1 : 0);
	}

	static boolean dfs(int u, int prev, int h) {
		if (h > 1_000_000) return false;
		for (int v : edges[u]) {
			if (v == prev) continue;
			if (A[v] >= h || dfs(v, u, 2 * h - A[v])) return true;
		}
		return false;
	}
}