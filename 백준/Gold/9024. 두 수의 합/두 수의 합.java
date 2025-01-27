import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++)
				A[n] = Integer.parseInt(st.nextToken());
			Arrays.sort(A);
			int curr = Integer.MAX_VALUE;
			int l = 0, r = N - 1;
			int cnt = 0;
			while (l < r) {
				int v = Math.abs(K - (A[r] + A[l]));
				if (curr == v)
					cnt++;
				else if (curr > v) {
					curr = v;
					cnt = 1;
				}
				int a = Math.abs(K - (A[r] + A[l + 1]));
				int b = Math.abs(K - (A[r - 1] + A[l]));
				if (a < b)
					l++;
				else
					r--;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}