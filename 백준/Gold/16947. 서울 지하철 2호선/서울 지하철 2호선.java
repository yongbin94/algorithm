import java.io.*;
import java.util.*;

public class Main {
	static int N, X;
	static int[] A;
	static boolean[] visited;
	static List<Integer>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		Arrays.fill(A, Integer.MAX_VALUE);
		visited = new boolean[N + 1];
		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		dfs(0, 1);
		bfs();
		print();
	}
	
	private static boolean dfs(int from, int u) {
		visited[u] = true;
		for(int v : edges[u]) {
			if(v == from) continue;
			if(visited[v]) {
				X = v;
				A[u] = 0;
				return true;
			}
			if(dfs(u, v)) {
				A[u] = 0;
				return u != X;
			}
		}		
		return false;
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int n = 1; n <= N; n++) {
			if (A[n] != 0) continue;
			q.offer(n);
		}
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : edges[u]) {
				if (A[v] != Integer.MAX_VALUE) continue;
				A[v] = A[u] + 1;
				q.offer(v);
			}
		}
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int n = 1; n <= N; n++) {
			sb.append(A[n]).append(" ");
		}
		System.out.println(sb);
	}
}
