import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			PriorityQueue<Coffee> pq = new PriorityQueue<>();
			for (int n = 0; n < N; n++) {
				pq.offer(new Coffee(new StringTokenizer(br.readLine())));
			}
			boolean[] A = new boolean[K];
			int res = 0;
			while (!pq.isEmpty()) {
				Coffee coffee = pq.poll();
				for (int t = coffee.t - 1; t >= 0; t--) {
					if (A[t]) continue;
					if (coffee.c-- == 0) break;
					A[t] = true;
					res += coffee.s;
				}
			}
			sb.append(String.format("Case #%d: %d\n", tc, res));
		}
		System.out.println(sb);
	}

	private static class Coffee implements Comparable<Coffee> {
		int c, t, s;

		public Coffee(StringTokenizer st) {
			this.c = Integer.parseInt(st.nextToken());
			this.t = Integer.parseInt(st.nextToken());
			this.s = Integer.parseInt(st.nextToken());
		}

		@Override
		public int compareTo(Coffee o) {
			return o.s - this.s;
		}
	}
}