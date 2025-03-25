import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] A;
	static int[] in;
	static List<Integer>[] L;
	static int[][] chg;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			A = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				A[n] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(br.readLine());
			chg = new int[M][2];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				chg[m][0] = Integer.parseInt(st.nextToken());
				chg[m][1] = Integer.parseInt(st.nextToken());
			}

			sb.append(solution()).append("\n");
		}
		System.out.println(sb);
	}

	private static String solution() {
		in = new int[N + 1];
		L = new ArrayList[N + 1];
		Arrays.setAll(L, v -> new ArrayList<>());

		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				L[A[i]].add(A[j]);
				in[A[j]]++;
			}
		}

		for (int[] arr : chg) {
			int a = arr[0];
			int b = arr[1];
			if (L[b].contains(a)) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			L[a].remove(Integer.valueOf(b));
			L[b].add(a);
			in[a]++;
			in[b]--;
		}

		Queue<Integer> q = new ArrayDeque<>();
		for (int n = 1; n <= N; n++) {
			if (in[n] == 0) {
				q.offer(n);
			}
		}

		List<Integer> res = new ArrayList<>();
		while (!q.isEmpty()) {
			if (q.size() != 1) {
				return "?";
			}

			int n = q.poll();
			res.add(n);

			for (int m : L[n]) {
				if (--in[m] == 0) {
					q.offer(m);
				}
			}
		}
		if (res.size() == N) {
			StringBuilder sb = new StringBuilder();
			for(int n : res) {
				sb.append(n).append(" ");
			}
			return sb.toString();
		} else {
			return "IMPOSSIBLE";
		}
	}
}