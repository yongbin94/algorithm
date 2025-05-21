import java.io.*;
import java.util.*;

public class Main {
	static int N, K, C;
	static int[] A, B;
	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		A = new int[N];
		B = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		answer = (long) K * Arrays.stream(A).max().getAsInt();
		recur(0, 0);
		System.out.println(answer);
	}

	private static void recur(int i, int c) {
		answer = binarySearch();
		if (i == N)
			return;
		for (int n = C - c; n >= 0; n--) {
			if (A[i] <= n)
				continue;
			B[i] += n;
			recur(i + 1, c + n);
			B[i] -= n;
		}
	}

	private static long binarySearch() {
	    long l = 1, r = answer;
	    while (l < r) {
	        long mid = l + (r - l) / 2;
	        if (calc(mid)) {
	            r = mid;
	        } else {
	            l = mid + 1;
	        }
	    }
	    return r;
	}

	private static boolean calc(long t) {
		long cnt = 0;
		for (int n = 0; n < N; n++) {
			cnt += t / (A[n] - B[n]);
		}
		return cnt >= K;
	}
}