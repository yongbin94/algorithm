import java.io.*;
import java.util.*;

public class Main {
	static int N, X;
	static long K;
	static int[] A;
	static long[] P, S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		A = new int[N + 1];
		P = new long[N + 1];
		S = new long[N + 2];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			P[i] = P[i - 1] + (long) A[i] * X;
		}

		for (int i = N; i >= 1; i--) {
			S[i] = S[i + 1] + A[i];
		}

		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		boolean flag = false;
		for (int i = 1; i <= N + 1; i++) {
			if (P[i - 1] + S[i] >= K) {
				flag = true;
				break;
			}
		}
		if (!flag) return -1;

		int l = 1, r = N - 1;
		int ans = 0;

		while (l <= r) {
			int mid = (l + r) / 2;
			if (check(mid)) {
				ans = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return ans == 0 ? -1 : ans;
	}

	private static boolean check(int v) {
		for (int i = 1; i <= N - v + 1; i++) {
			int j = i + v - 1;
			if (P[i - 1] + S[j + 1] >= K)
				return true;
		}
		return false;
	}
}
