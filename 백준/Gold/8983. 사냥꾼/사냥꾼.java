import java.io.*;
import java.util.*;

public class Main {
	static int M, N, L;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		A = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			A[m] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);

		int answer = 0;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (y > L) continue;
			
			int i = binarySearch(x);
			if (i < M && Math.abs(A[i] - x) + y <= L) answer++;
			else if (i > 0 && Math.abs(A[i - 1] - x) + y <= L) answer++;
		}
		System.out.println(answer);
	}

	private static int binarySearch(int x) {
		int l = 0, r = M - 1;

		while (l < r) {
			int mid = l + (r - l) / 2;

			if (A[mid] >= x) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}
}