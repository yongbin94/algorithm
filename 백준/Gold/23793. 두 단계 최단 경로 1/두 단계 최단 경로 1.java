import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Edge>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[u].add(new Edge(v, w));
		}

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int z = Integer.parseInt(st.nextToken());

		long xy = dijkstra(x, y, 0);
		long yz = dijkstra(y, z, 0);
		long res1 = (xy == -1 || yz == -1) ? -1 : xy + yz;
		long res2 = dijkstra(x, z, y);

		System.out.println(res1 + " " + res2);
	}

	private static long dijkstra(int s, int e, int skip) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		long[] memo = new long[N + 1];
		Arrays.fill(memo, Long.MAX_VALUE);

		if (s == skip) return -1;

		memo[s] = 0;
		pq.offer(new Edge(s, 0));

		while (!pq.isEmpty()) {
			Edge u = pq.poll();

			if (u.w > memo[u.n]) continue;
			if (u.n == e) return u.w;

			for (Edge v : edges[u.n]) {
				if (v.n == skip) continue;
				long nw = u.w + v.w;
				if (memo[v.n] <= nw) continue;
				memo[v.n] = nw;
				pq.offer(new Edge(v.n, nw));
			}
		}
		return -1;
	}

	private static class Edge implements Comparable<Edge> {
		int n;
		long w;

		public Edge(int n, long w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}
}