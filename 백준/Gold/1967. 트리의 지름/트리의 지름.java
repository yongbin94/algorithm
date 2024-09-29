import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] A;
	static List<Edge>[] edges; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N];
		for(int i = 0; i < N; i++)
			edges[i] = new ArrayList<>();
		for(int n= 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, w));
			edges[b].add(new Edge(a, w));
		}
		bfs(0);
		int idx = 0, max = 0;;
		for(int i = 0; i < N; i++) {
			if(max < A[i]) {
				idx = i;
				max = A[i];
			}
		}
		bfs(idx);
		System.out.println(Arrays.stream(A).max().getAsInt());
	}
	
	private static void bfs(int a) {
		Queue<Edge> q = new ArrayDeque<>();
		q.offer(new Edge(a, 0));
		A = new int[N];
		Arrays.fill(A, Integer.MAX_VALUE);
		while(!q.isEmpty()) {
			Edge s = q.poll();
			A[s.e] = s.w;
			for(Edge e : edges[s.e]) {
				if(A[e.e] != Integer.MAX_VALUE)
					continue;
				q.offer(new Edge(e.e, s.w + e.w)); 
			}
		}
	}

	private static class Edge{
		int e, w;

		public Edge(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
}