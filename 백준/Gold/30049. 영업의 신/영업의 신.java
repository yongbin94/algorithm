import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, cnt;
	static Employee[] E;
	static int[] J, T, best;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		E = new Employee[N + 1];
		J = new int[M + 1];
		T = new int[M + 1];
		best = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			E[n] = new Employee(n, new StringTokenizer(br.readLine()));
		}
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			E[i].update(j, v);
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static class Employee {
		int i;
		Map<Integer, Integer> map;

		public Employee(int n, StringTokenizer st) {
			i = n;
			map = new HashMap<>();
			for (int k = 0; k < K; k++) {
				int j = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				map.put(j, 0);
				update(j, t);
			}
		}

		public void update(int j, int v) {
			v += map.get(j);
			map.put(j, v);
			if (T[j] < v) {
				if (best[J[j]]-- == K) cnt--;
				if (++best[i] == K) cnt++;
				J[j] = i;
				T[j] = v;
			}
		}
	}
}