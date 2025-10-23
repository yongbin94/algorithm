import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		Log[] L = new Log[N];
		int[] A = new int[N + 1];

		for (int n = 0; n < N; n++) {
			L[n] = new Log(n + 1, new StringTokenizer(br.readLine()));
		}
		Arrays.sort(L, (o1, o2) -> o2.e - o1.e);
		int g = 0, v = Integer.MAX_VALUE;
		for (Log log : L) {
			if (log.e < v) g++;
			v = Math.min(v, log.s);
			A[log.n] = g;
		}
		StringBuilder sb = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(A[a] == A[b] ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}

	private static class Log {
		int n, s, e;

		public Log(int n, StringTokenizer st) {
			this.n = n;
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
		}
	}
}
