import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Edge>[] edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, t, w));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));

		int[] memo = new int[N + 1];
		Arrays.fill(memo, Integer.MAX_VALUE);
		memo[1] = 0;

		while (!pq.isEmpty()) {
			Node u = pq.poll();
			if (memo[u.n] < u.w) continue;
			if (u.n == N) {
				System.out.println(u.w);
				return;
			}

			for (Edge e : edges[u.n]) {
				int nw = u.w + ((e.w - (u.w % e.w)) % e.w) + e.t;
				if (memo[e.n] > nw) {
					memo[e.n] = nw;
					pq.offer(new Node(e.n, nw));
				}
			}
		}
	}

	private static class Edge {
		int n, t, w;

		public Edge(int n, int t, int w) {
			this.n = n;
			this.t = t;
			this.w = w;
		}
	}

	private static class Node implements Comparable<Node> {
		int n, w;

		public Node(int n, int w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
}