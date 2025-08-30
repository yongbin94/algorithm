import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int m = 0; m < n; m++) {
				pq.offer(new Edge(n, m, Integer.parseInt(st.nextToken())));
			}
			parent[n] = n;
		}
		long answer = 0;
		while (!pq.isEmpty() && N > 1) {
			Edge e = pq.poll();
			if (union(e.u, e.v)) {
				answer += e.w;
				N--;
			}
		}
		System.out.println(answer);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return false;
		parent[rootB] = rootA;
		return true;
	}

	private static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	private static class Edge implements Comparable<Edge> {
		int u, v, w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
}