import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] in, A;
	static List<Vaccine>[] V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		in = new int[N + 1];
		A = new int[N + 1];
		V = new ArrayList[N + 1];
		Arrays.setAll(V, v -> new ArrayList<>());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			V[s].add(new Vaccine(e, w));
			in[e]++;
		}
		Queue<Integer> q = new ArrayDeque<>();
		for (int n = 1; n <= N; n++) {
			if (in[n] == 0) {
				q.offer(n);
                A[n] = 1;
			}
		}

		while (!q.isEmpty()) {
			int s = q.poll();
			for (Vaccine e : V[s]) {
				A[e.e] = Math.max(A[e.e], A[s] + e.w + (e.w >= 7 ? 1 : 0));
				if (--in[e.e] == 0) {
					q.offer(e.e);
				}
			}
		}

		System.out.println(Arrays.stream(A).max().getAsInt());
	}

	private static class Vaccine {
		int e, w;

		public Vaccine(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
}