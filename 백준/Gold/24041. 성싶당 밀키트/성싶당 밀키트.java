import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long G, K;
	static long[] S, L;
	static boolean[] O;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		G = Long.parseLong(st.nextToken());
		K = Long.parseLong(st.nextToken());
		S = new long[N + 1];
		L = new long[N + 1];
		O = new boolean[N + 1];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			S[n] = Long.parseLong(st.nextToken());
			L[n] = Long.parseLong(st.nextToken());
			O[n] = st.nextToken().charAt(0) == '1';
		}
		System.out.println(binarySearch());
	}

	private static long binarySearch() {
		long l = 0;
		long r = Arrays.stream(L).max().getAsLong() + G;
		while (l < r) {
			long mid = l + (r - l + 1) / 2;
			if (calc(mid)) l = mid;
			else r = mid - 1;
		}
		return l;
	}

	private static boolean calc(long x) {
		long total = 0;
		PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> Long.compare(b, a));
		
		for (int i = 0; i < N; i++) {
			long v = S[i] * Math.max(1, x - L[i]);
			total += v;
			if(O[i]) pq.offer(v);
		}
		
		for (int k = 0; k < K && !pq.isEmpty(); k++) {
			total -= pq.poll();
		}
		
		return total <= G;
	}
}
