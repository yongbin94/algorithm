import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static long[] map;
	static List<Edge> edges;
	static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new long[N + 1];
		edges = new ArrayList<>();

		Arrays.fill(map, INF);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.add(new Edge(s, e, w));
		}

		if (!solution(1)) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= N; i++) {
				if (map[i] == INF)
					sb.append("-1\n");
				else
					sb.append(map[i]).append("\n");

			}
			System.out.println(sb);
		}
	}

	private static boolean solution(int start) {
		map[start] = 0;

		for (int i = 1; i < N; i++)
			for (Edge edge : edges)
				if (map[edge.s] != INF && map[edge.s] + edge.w < map[edge.e])
					map[edge.e] = map[edge.s] + edge.w;

		for (Edge edge : edges)
			if (map[edge.s] != INF && map[edge.s] + edge.w < map[edge.e])
				return false;

		return true;
	}

	private static class Edge {
		int s, e, w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}