import java.io.*;
import java.util.*;

public class Main {
	static int N, M, S, E;
	static ArrayList<Integer>[] adj;
	static boolean[] v;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++)
			Collections.sort(adj[i]);

		int d1 = bfs(S, E);

		boolean[] used = new boolean[N + 1];
		int curr = p[E];
		while (curr != S) {
			used[curr] = true;
			curr = p[curr];
		}

		v = used;
		int d2 = bfs(E, S);

		System.out.println(d1 + d2);
	}

	static int bfs(int start, int end) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { start, 0 });
		
		if (v == null) v = new boolean[N + 1];
		else {
			boolean[] next = new boolean[N + 1];
			for (int i = 1; i <= N; i++)
				if (v[i]) next[i] = true;
			v = next;
		}

		p = new int[N + 1];
		v[start] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int n = curr[0];
			int d = curr[1];

			if (n == end) return d;

			for (int next : adj[n]) {
				if (!v[next]) {
					v[next] = true;
					p[next] = n;
					q.add(new int[] { next, d + 1 });
				}
			}
		}
		return 0;
	}
}