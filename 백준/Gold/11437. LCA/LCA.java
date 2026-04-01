import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] parent, depth;
	static List<Integer>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		for (int n = 1; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		parent = new int[N + 1];
		depth = new int[N + 1];

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		depth[1] = 1;
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : edges[u]) {
				if (depth[v] != 0)
					continue;
				depth[v] = depth[u] + 1;
				parent[v] = u;
				q.offer(v);
			}
		}

		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			while (true) {
				while (depth[a] > depth[b]) a = parent[a];
				while (depth[b] > depth[a]) b = parent[b];
				if (a == b) break;
				a = parent[a];
			}
			sb.append(a).append("\n");
		}
		System.out.println(sb);
	}
}