import java.io.*;
import java.util.*;

public class Main {
	static List<Edge>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		for (int n = 1; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, v));
			edges[b].add(new Edge(a, v));
		}
		System.out.println(recur(1, 0, Integer.MAX_VALUE));
	}

	private static int recur(int curr, int prev, int cost) {
		if (curr != 1 && edges[curr].size() == 1) {
			return cost;
		}

		int sum = 0;
		for (Edge next : edges[curr]) {
			if (next.n == prev) continue;
			sum += recur(next.n, curr, next.v);
		}
		if (curr == 1)
			return sum;
		return Math.min(cost, sum);
	}

	private static class Edge {
		int n, v;

		public Edge(int n, int v) {
			this.n = n;
			this.v = v;
		}
	}
}