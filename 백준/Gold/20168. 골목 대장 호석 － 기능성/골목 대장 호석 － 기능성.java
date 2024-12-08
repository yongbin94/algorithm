import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;
	static int N, M, A, B, C;
	static List<Edge>[] edges;
	static int[] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N + 1];
		memo = new int[N + 1];
		for (int n = 0; n <= N; n++)
			edges[n] = new ArrayList<>();
		int max = 0;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, w));
			edges[b].add(new Edge(a, w));
			max = Math.max(max, w);
		}
		int answer = -1, l = 0, r = max;
		while (l <= r) {
			int mid = (r + l) / 2;
			if (solution(mid)) {
				answer = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		System.out.println(answer);
	}

	private static boolean solution(int c) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(A, 0));
		Arrays.fill(memo, INF);
		memo[A] = 0;
		while (!pq.isEmpty()) {
			Edge s = pq.poll();
			if (s.e == B)
				return true;
			for (Edge e : edges[s.e]) {
				if (C < e.w + memo[s.e])
					continue;
				if (c < e.w || memo[e.e] <= e.w + memo[s.e])
					continue;
				memo[e.e] = e.w + memo[s.e];
				pq.offer(new Edge(e.e, e.w + memo[s.e]));
			}
		}
		return false;
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