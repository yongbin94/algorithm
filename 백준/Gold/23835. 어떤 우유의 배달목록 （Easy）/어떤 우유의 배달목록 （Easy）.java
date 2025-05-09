import java.io.*;
import java.util.*;

public class Main {
	static int N, Q;
	static List<Integer>[] edges;
	static int[] milk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		milk = new int[N + 1];
		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			if (t == 1) delivery(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			else sb.append(milk[Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.println(sb);
	}

	private static void delivery(int a, int b) {
		boolean[] visited = new boolean[N + 1];
		int[] prev = new int[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(a);
		visited[a] = true;
		int cnt = 0;
		outer:
		for (; !q.isEmpty(); cnt++) {
			for (int i = 0, size = q.size(); i < size; i++) {
				int u = q.poll();
				if (u == b) break outer;
				for (int v : edges[u]) {
					if (visited[v]) continue;
					visited[v] = true;
					prev[v] = u;
					q.offer(v);
				}
			}
		}
		while (b != 0) {
			milk[b] += cnt--;
			b = prev[b];
		}
	}
}