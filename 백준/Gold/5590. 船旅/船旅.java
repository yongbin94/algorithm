import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] memo;
	static List<Edge>[] edges;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memo = new int[N + 1][N + 1];
		Arrays.stream(memo).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().charAt(0) == '0') {
				dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges[a].add(new Edge(b, w));
				edges[b].add(new Edge(a, w));
			}
		}
		System.out.println(sb);
	}

	private static void dijkstra(int a, int b) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(a, 0));
		while (!pq.isEmpty()) {
			Edge s = pq.poll();
			for (Edge e : edges[s.e]) {
				int nw = s.w + e.w;
				if (memo[a][e.e] < nw)
					continue;
				memo[a][e.e] = nw;
				memo[e.e][a] = nw;
				pq.offer(new Edge(e.e, nw));
			}
		}
		sb.append(memo[a][b] == Integer.MAX_VALUE ? -1 : memo[a][b]).append("\n");
	}

	private static class Edge implements Comparable<Edge> {
		int e, w;

		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}