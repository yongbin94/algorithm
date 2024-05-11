import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static long[] min;
	static List<CW>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = new long[N + 1];
		Arrays.fill(min, Long.MAX_VALUE);
		list = new ArrayList[N + 1];
		for (int n = 1; n <= N; n++)
			list[n] = new ArrayList<>();
		for (int w = 1; w <= M; w++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list[s].add(new CW(e, w));
			list[e].add(new CW(s, w));
		}
		solution();
	}

	private static void solution() {
		PriorityQueue<CW> pq = new PriorityQueue<>();
		pq.offer(new CW(1, 0));
		min[1] = 0;
		while (!pq.isEmpty()) {
			CW s = pq.poll();
			if (s.n == N) {
				System.out.println(s.w);
				return;
			}
			if (s.w > min[s.n])
				continue;
			for (CW e : list[s.n]) {
				long w = ((s.w / M) + (s.w % M > e.w ? 1 : 0)) * M + e.w;
				if (min[e.n] <= w)
					continue;
				min[e.n] = w;
				pq.offer(new CW(e.n, w));
			}

		}
	}

	private static class CW implements Comparable<CW> {
		int n;
		long w;

		public CW(int n, long w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(CW o) {
			return Long.compare(this.w, o.w);
		}
	}
}
