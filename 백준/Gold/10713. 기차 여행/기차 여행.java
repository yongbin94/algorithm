import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = new int[N + 1];
		int[] B = new int[N + 1];
		int[] C = new int[N + 1];
		long[] cnt = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		while (st.hasMoreTokens()) {
			int curr = Integer.parseInt(st.nextToken());
			cnt[Math.min(prev, curr)]++;
			cnt[Math.max(prev, curr)]--;
			prev = curr;
		}
		
		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			A[n] = Integer.parseInt(st.nextToken());
			B[n] = Integer.parseInt(st.nextToken());
			C[n] = Integer.parseInt(st.nextToken());
		}

		long res = 0;
		for (int n = 1; n <= N; n++) {
			cnt[n] += cnt[n - 1];
			res += Math.min(A[n] * cnt[n], B[n] * cnt[n] + C[n]);
		}
		System.out.println(res);
	}
}
