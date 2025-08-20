import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		List<Edge>[] edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c));
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0));
		int[] memo = new int[N + 1];
		Arrays.fill(memo, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N + 1];
		int answer = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int u = edge.v;
			if (visited[u]) continue;
			visited[u] = true;
			answer += edge.w;
			if (--N == 0) {
				System.out.println(answer);
				return;
			}
			for (Edge v : edges[u]) {
				if (visited[v.v] || memo[v.v] <= v.w) continue;
				memo[v.v] = v.w;
				pq.offer(v);
			}
		}
	}

	private static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}