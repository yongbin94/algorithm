import java.io.*;
import java.util.*;

public class Main {
	static int N, P, C;
	static int[] A;
	static List<Edge>[] edges;
	static final int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		edges = new ArrayList[P + 1];
		for (int i = 1; i <= P; i++)
			edges[i] = new ArrayList<>();

		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[u].add(new Edge(v, w));
			edges[v].add(new Edge(u, w));
		}

		int res = Integer.MAX_VALUE;
		for (int i = 1; i <= P; i++) {
			res = Math.min(res, dijkstra(i));
		}
		System.out.println(res);
	}

	static int dijkstra(int s) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] memo = new int[P + 1];
		Arrays.fill(memo, INF);
		memo[s] = 0;
		pq.offer(new Edge(s, 0));
		
		while (!pq.isEmpty()) {
			Edge u = pq.poll();
			if (u.w > memo[u.n]) continue;
			for (Edge v : edges[u.n]) {
				if (memo[v.n] <= memo[u.n] + v.w) continue;
				memo[v.n] = memo[u.n] + v.w;
				pq.offer(new Edge(v.n, memo[v.n]));	
			}
		}

		int res = 0;
		for (int v : A) {
			if (memo[v] == INF) return Integer.MAX_VALUE;
			res += memo[v];
		}
		return res;
	}

	static class Edge implements Comparable<Edge> {
		int n, w;

		Edge(int n, int w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}