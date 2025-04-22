import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		StringTokenizer p = new StringTokenizer(br.readLine());
		StringTokenizer t = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int n = 0; n < N; n++) {
			A[n] = Math.max(0, Integer.parseInt(t.nextToken()) - Integer.parseInt(p.nextToken()));
		}
		long res = 0;
		int prev = 0, cnt = 0;
		Queue<Flag> q = new ArrayDeque<>();
		for (int n = 0; n < N; n++) {
			prev -= cnt;
			while (!q.isEmpty() && q.peek().i <= n) {
				cnt -= q.poll().k;
			}
			if (prev >= A[n]) continue;
			int a = (A[n] - 1 - prev) / K + 1;
			q.offer(new Flag(n + K, a));
			res += a;
			cnt += a;
			prev += K * a;
		}
		System.out.println(res);
	}

	private static class Flag {
		int i, k;

		public Flag(int i, int k) {
			this.i = i;
			this.k = k;
		}
	}
}