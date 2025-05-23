import java.util.*;

public class Main {
	static int N;
	static int[] A;

	public static void main(String[] args) {
		N = new Scanner(System.in).nextInt();
		A = new int[N];
		recur(0);
		print();
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		Arrays.stream(A).forEach(v -> sb.append(v));
		System.out.println(sb);
	}

	private static boolean recur(int n) {
		if (n == N) return true;
		for (int i = 1; i <= 3; i++) {
			A[n] = i;
			if (!check(n)) continue;
			if (recur(n + 1)) return true;
		}
		return false;
	}

	private static boolean check(int n) {
		outer: for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (0 > n - i * 2 + 1) return true;
				if (A[n - i + 1 + j] != A[n - i * 2 + 1 + j]) continue outer;
			}
			return false;
		}
		return true;
	}
}