import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] H, in, memo;
	static List<Integer>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = new int[N + 1];
		in = new int[N + 1];
		memo = new int[N + 1];
		edges = new ArrayList[N + 1];
		Arrays.setAll(edges, v -> new ArrayList<>());
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			H[n] = Integer.parseInt(st.nextToken());
		}
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (H[a] > H[b]) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			in[a]++;
			edges[b].add(a);
		}
		PriorityQueue<Info> pq = new PriorityQueue<>();
		for (int n = 1; n <= N; n++) {
			if (in[n] == 0) {
				pq.offer(new Info(n, 1));
				memo[n] = 1;
			}
		}
		while (!pq.isEmpty()) {
			Info u = pq.poll();
			if (memo[u.n] > u.w) continue;
			for (int v : edges[u.n]) {
				if (memo[v] > u.w) continue;
				memo[v] = u.w + 1;
				pq.offer(new Info(v, u.w + 1));
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int n = 1; n <= N; n++) {
			sb.append(memo[n]).append("\n");
		}
		System.out.println(sb);
	}

	private static class Info implements Comparable<Info> {
		int n, w;

		public Info(int n, int w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(Info o) {
			return H[o.n] != H[this.n] ? H[o.n] - H[this.n] : o.w - this.w;
		}
	}
}