import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			edges = new ArrayList[N + 1];
			Arrays.setAll(edges, v -> new ArrayList<>());
			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				edges[a].add(b);
				edges[b].add(a);
			}
			sb.append(solution() ? "possible\n" : "impossible\n");
		}
		System.out.println(sb);
	}

	private static boolean solution() {
		boolean[] visited = new boolean[N + 1];
		boolean[] color = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(i);
			visited[i] = true;
			while (!q.isEmpty()) {
				int u = q.poll();
				for (int v : edges[u]) {
					if (visited[v]) {
						if (color[u] == color[v]) return false;
						continue;
					}
					q.offer(v);
					visited[v] = true;
					color[v] = !color[u];
				}
			}
		}
		return true;
	}
}