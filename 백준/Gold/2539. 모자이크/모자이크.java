import java.io.*;
import java.util.*;

public class Main {
	static int N, M, X, max;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());
		A = new int[T];
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			max = Math.max(max, n);
			A[t] = m;
		}
		Arrays.sort(A);
		System.out.println(binarySearch());
	}

	private static int binarySearch() {
	    int l = max, r = Math.max(N, M);

	    while (l <= r) {
	        int mid = (l + r) / 2;
	        if (check(mid)) r = mid - 1;
	        else l = mid + 1;
	    }
	    return l;
	}

	private static boolean check(int v) {
		int x = 0, i = 0;

		while (i < A.length) {
			int end = A[i] + v - 1;
			while (i < A.length && A[i] <= end) i++;
			if (++x > X) return false;
		}
		return true;
	}
}
