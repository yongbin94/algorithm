import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static long X;
	static long[] A, dp;
	static List<Integer>[] L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Long.parseLong(st.nextToken());
		A = new long[N + 1];
		dp = new long[N + 1];
		Arrays.fill(dp, Long.MAX_VALUE);

		L = new ArrayList[N + 1];
		Arrays.setAll(L, v -> new ArrayList<>());
		PriorityQueue<Ball> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			int p = Integer.parseInt(st.nextToken());
			if (p == -1) {
                if(A[n] > X) continue;
                pq.offer(new Ball(n, A[n]));
                dp[n] = A[n];
			} else {
				L[p].add(n);
			}
		}
		if (pq.isEmpty()) answer = -1;
		while (!pq.isEmpty()) {
			Ball b = pq.poll();
			answer = Math.max(answer, b.n);
			if (dp[b.n] < b.p) continue;
			for (int n : L[b.n]) {
				long p = b.p + A[n]; 
				if(p > X || dp[n] <= p) continue;
				dp[n] = p;
				pq.offer(new Ball(n, p));
			}
		}
		System.out.println(answer);
	}

	private static class Ball implements Comparable<Ball> {
		int n;
		long p;

		public Ball(int n, long p) {
			this.n = n;
			this.p = p;
		}

		@Override
		public int compareTo(Ball o) {
			return Long.compare(this.p, o.p);
		}

	}
}