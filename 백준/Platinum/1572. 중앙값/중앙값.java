import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		SegTree seg = new SegTree(65536, K);
		for (int n = 1; n <= N; n++) {
			seg.add(Integer.parseInt(br.readLine()));
		}
		System.out.println(seg.res);
	}

	private static class SegTree {
		int N, K;
		long[] tree;
		Queue<Integer> queue = new ArrayDeque<>();
		long res = 0;

		public SegTree(int n, int k) {
			N = 1;
			while (N < n) N <<= 1;
			tree = new long[N * 2];
			K = k;
		}

		private void add(int i) {
			update(i, 1);
			queue.offer(i);
			if (queue.size() == K) {
				query((K + 1) / 2);
				update(queue.poll(), -1);
			}
		}

		private void update(int i, int v) {
			for (int idx = N + i; idx > 0; idx >>= 1) tree[idx] += v;
		}

		private void query(int v) {
			int idx = 1;
			while (idx < N) {
				idx *= 2;
				if (tree[idx] < v) v -= tree[idx++];
			}
			res += idx - N;
		}
	}
}
