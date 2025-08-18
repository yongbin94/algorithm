import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] S = new long[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			S[n] += S[n - 1] + Long.parseLong(st.nextToken());
		}
		int K = 0;
		long min = Long.MAX_VALUE;
		for (int k = 1; k <= N / 2; k++) {
			long[] A = new long[N - k + 1];
			for (int i = 0; i <= N - k; i++) {
				A[i] = S[i + k] - S[i];
			}
			for (int i = 0; i < N - k; i++) {
				for (int j = i + k; j <= N - k; j++) {
					long v = Math.abs(A[i] - A[j]);
					if(min >= v) {
						min = v;
						K = k;
					}
				}
			}
		}
		System.out.println(K);
		System.out.println(min);
	}
}