import java.io.*;
import java.util.*;

public class Main {
	static int V;
	static List<Edge>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		edges = new ArrayList[V + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, w));
			edges[b].add(new Edge(a, w));
		}
		System.out.println(dijkstra(1, V) == dijkstra(1, P) + dijkstra(P, V) ? "SAVE HIM" : "GOOD BYE");
	}

	private static int dijkstra(int s, int e) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] memo = new int[V + 1];
		Arrays.fill(memo, 1_000_000_000);
		pq.offer(new Edge(s, 0));
		memo[s] = 0;
		while (!pq.isEmpty()) {
			Edge u = pq.poll();
			if (u.w > memo[u.n]) continue;
			if (u.n == e) return u.w;

			for (Edge v : edges[u.n]) {
				int nw = u.w + v.w;
				if (memo[v.n] <= nw) continue;
				memo[v.n] = nw;
				pq.offer(new Edge(v.n, nw));
			}
		}
		return -1;
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