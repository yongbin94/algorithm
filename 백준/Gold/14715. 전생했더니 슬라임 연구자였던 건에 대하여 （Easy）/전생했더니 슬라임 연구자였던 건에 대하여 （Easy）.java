import java.util.*;

public class Main {
	public static void main(String[] args) {
		int N = new Scanner(System.in).nextInt();
		boolean[] C = new boolean[N + 1];
		for (int n = 2; n * n <= N; n++) {
			if (C[n]) continue;
			for (int m = n * n; m <= N; m += n) {
				C[m] = true;
			}
		}
		final int INF = 100_000_000;
		int[] A = new int[N + 1];
		for (int i = 2; i <= N; i++) {
			if (C[i]) A[i] = INF;
		}

		for (int n = 2; n <= N / 2 + 1; n++) {
			for (int m = 2; m * n <= N; m++) {
				A[n * m] = Math.min(A[n * m], Math.max(A[n], A[m]) + 1);
			}
		}
		System.out.println(A[N] == INF ? 0 : A[N]);
	}
}