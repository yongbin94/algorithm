import java.io.*;
import java.util.*;

public class Main {
	static int N, M, S, E;
	static List<Edge>[] edges;
	static int[] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N + 1];
		A = new int[N + 1];
		A[S] = Integer.MAX_VALUE;
		Arrays.setAll(edges, v -> new ArrayList<>());
		while (M-- > 0) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, w));
			edges[b].add(new Edge(a, w));
		}
		solution();
	}

	private static void solution() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(S, 0));
		while(!pq.isEmpty()) {
			Edge u = pq.poll();
			if(u.v == E) {
				System.out.println(u.w);
				return;
			}
			for(Edge v : edges[u.v]) {
				int nw = Math.min(A[u.v], v.w);
				if(A[v.v] >= nw) continue;
				A[v.v] = nw;
				pq.offer(new Edge(v.v, nw));
			}
		}
        System.out.println(0);
	}

	private static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return o.w - this.w;
		}
	}
}
