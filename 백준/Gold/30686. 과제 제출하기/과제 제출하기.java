import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static int[] D;
	static List<Integer>[] K;
	static int[] selected;
	static boolean[] used;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;
		D = new int[N + 1];
		K = new ArrayList[M];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			D[n] = Integer.parseInt(st.nextToken());
		}
		for (int m = 0; m < M; m++) {
			K[m] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			for (int i = 0; i < a; i++) {
				K[m].add(Integer.parseInt(st.nextToken()));
			}
		}
		selected = new int[M];
		used = new boolean[M];
		Arrays.fill(selected, -1);
		recur(0);
		System.out.println(answer);
	}

	private static void recur(int idx) {
		if (idx == M) {
			answer = Math.min(answer, solution());
			return;
		}
		for (int i = 0; i < M; i++) {
			if (used[i]) continue;
			used[i] = true;
			selected[idx] = i;
			recur(idx + 1);
			used[i] = false;
		}
	}

	private static int solution() {
		int res = 0;
		int[] A = new int[N + 1];
		for (int i = 0; i < M; i++) {
			for (int v : K[selected[i]]) {
				if (A[v] <= i) {
					res++;
					A[v] = i + D[v];
				}
			}
		}
		return res;
	}
}