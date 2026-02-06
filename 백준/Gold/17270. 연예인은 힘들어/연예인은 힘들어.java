import java.io.*;
import java.util.*;

public class Main {
	static int V, M;
	static List<Edge>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[V + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c));
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[] A = new int[V + 1];
		int[] B = new int[V + 1];
		dijkstra(A, a);
		dijkstra(B, b);

		int minSum = Integer.MAX_VALUE;
		for (int v = 1; v <= V; v++) {
			if (v == a || v == b) continue;
			minSum = Math.min(minSum, A[v] + B[v]);
		}

		int minA = Integer.MAX_VALUE;
		int res = -1;
		for (int v = 1; v <= V; v++) {
			if (v == a || v == b) continue;
			int aa = A[v];
			int bb = B[v];
			int sum = aa + bb;

			if (sum == minSum && aa <= bb) {
				if (aa < minA) {
					minA = aa;
					res = v;
				}
			}
		}
		System.out.println(res);
	}

	private static void dijkstra(int[] memo, int n) {
		Arrays.fill(memo, Integer.MAX_VALUE);
		memo[n] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(n, 0));
		while (!pq.isEmpty()) {
			Edge u = pq.poll();
			if (u.w > memo[u.n])
				continue;
			for (Edge v : edges[u.n]) {
				int nw = u.w + v.w;
				if (memo[v.n] <= nw)
					continue;
				memo[v.n] = nw;
				pq.offer(new Edge(v.n, nw));
			}
		}
	}

	private static class Edge implements Comparable<Edge> {
		int n, w;

		public Edge(int n, int w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}